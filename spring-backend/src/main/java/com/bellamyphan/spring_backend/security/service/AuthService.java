package com.bellamyphan.spring_backend.security.service;

import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import com.bellamyphan.spring_backend.security.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String authenticateAndGenerateToken(String username, String rawPassword) {
        User userFromDb = userService.findByUsernameWithRoles(username);
        if (!passwordEncoder.matches(rawPassword, userFromDb.getPassword())) {
            logger.warn("Invalid password for user: {}", username);
            throw new UnauthorizedException("Invalid username or password");
        }
        logger.info("User authenticated successfully: {}", username);
        String token = jwtService.generateToken(userFromDb.getUsername(), userFromDb.getRoles());
        logger.debug("Generated JWT token for user: {}", username);
        return token;
    }
}
