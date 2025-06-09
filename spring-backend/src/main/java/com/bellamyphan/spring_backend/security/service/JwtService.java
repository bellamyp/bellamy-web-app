package com.bellamyphan.spring_backend.security.service;

import com.bellamyphan.spring_backend.dbuser.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private Key ENCRYPTED_KEY;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.token.expiration.minutes}")
    private int tokenExpirationMinutes;

    @PostConstruct
    public void init() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new RuntimeException("JWT_SECRET environment variable is not set");
        }
        if (tokenExpirationMinutes <= 0) {
            throw new RuntimeException("Token expiration minutes must be greater than 0");
        }
        ENCRYPTED_KEY = Keys.hmacShaKeyFor(jwtSecret.trim().getBytes());
    }

    public String generateToken(String username, Set<Role> roles) {
        Map<String, Object> claims = new HashMap<>();
        // Convert roles to a Set of role names
        claims.put("roles", roles.stream()
                .map(Role::getName) // Extract role names
                .collect(Collectors.toSet()));
        logger.info("Generating token for username: {}", username);
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * tokenExpirationMinutes))
                .signWith(ENCRYPTED_KEY)
                .compact();
    }

    // Handle token authorization
    public String authorizeToken(String token) {
        try {
            String username = extractUsername(token);
            if (validateToken(token, username)) {
                return username; // Return the username if the token is valid
            }
        } catch (Exception e) {
            logger.error("Invalid token: {}", e.getMessage());
        }
        return null; // Return null if the token is invalid
    }

    public Set<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(ENCRYPTED_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        @SuppressWarnings("unchecked")
        List<String> rolesList = (List<String>) claims.get("roles");
        return new HashSet<>(rolesList); // Convert List to Set<String>
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(ENCRYPTED_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    private String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(ENCRYPTED_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
