package com.bellamyphan.spring_backend.security.controller;

import com.bellamyphan.spring_backend.security.dto.LoginRequest;
import com.bellamyphan.spring_backend.security.service.AuthService;
import com.bellamyphan.spring_backend.security.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // Logger initialization
    private final AuthService authService; // Inject AuthService for authentication logic

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.username());
        String token = authService.authenticateAndGenerateToken(
                loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
