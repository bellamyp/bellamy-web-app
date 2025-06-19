package com.bellamyphan.spring_backend.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    String[] allowedOrigins = {
            "http://localhost:4200",
            "http://frontend:4200",
            "http://bellamy-web-angular2.s3-website.us-east-2.amazonaws.com"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Define the allowed origins for CORS
        logger.info("Configuring CORS settings for /api/** and specific login/logout endpoints");
        // Allow cross-origin requests for all endpoints under /api/**
        registry.addMapping("/api/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        logger.debug("CORS configuration for /api/** set with allowed origins: {}", (Object) allowedOrigins);
        // Allow cross-origin requests for /login endpoint
        registry.addMapping("/login")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true);
        logger.debug("CORS configuration for /login set with allowed origins: {}", (Object) allowedOrigins);
        // Allow cross-origin requests for /logout endpoint
        registry.addMapping("/logout")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true);
        logger.debug("CORS configuration for /logout set with allowed origins: {}", (Object) allowedOrigins);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        logger.info("Setting up global CORS configuration with origins: {}", (Object) allowedOrigins);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        logger.debug("Global CORS configuration registered for all endpoints: /**");
        return source;
    }
}