package com.bellamyphan.spring_backend.security.filter;

import com.bellamyphan.spring_backend.dbuser.service.UserService;
import com.bellamyphan.spring_backend.security.service.JwtService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TokenAuthorizationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthorizationFilter.class);
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = extractToken(httpRequest);
        if (token != null) {
            logger.debug("JWT token extracted: {}", token);
            String username = jwtService.authorizeToken(token);
            if (username != null) {
                try {
                    // ✅ Check if the user exists (throws if not found)
                    userService.loadUserByUsername(username);

                    logger.debug("Token authorized for user: {}", username);
                    Set<String> roles = jwtService.extractRoles(token);
                    logger.debug("Extracted roles from token: {}", roles);

                    // ✅ Log token usage and URI being accessed
                    logger.info("Token used by: '{}', request to: {}", username, httpRequest.getRequestURI());

                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList();
                    var authentication = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("User '{}' authenticated with roles: {}", username, roles);
                } catch (UsernameNotFoundException e) {
                    logger.warn("Valid token but user '{}' not found in UserDB", username);
                    // Optionally: clear the context just in case
                    SecurityContextHolder.clearContext();
                }
            } else {
                logger.warn("Token authorization failed");}
        } else {
            logger.debug("No valid Authorization header found");}
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
