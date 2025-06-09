package com.bellamyphan.spring_backend.security.config;

import com.bellamyphan.spring_backend.security.filter.TokenAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final TokenAuthorizationFilter tokenAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");

        http
                // Enable CORS using WebConfig's configuration
                .cors(cors -> cors.configurationSource(new WebConfig().corsConfigurationSource()))

                // Disable CSRF (not needed for stateless JWT)
                .csrf(AbstractHttpConfigurer::disable)

                // Define authorization rules
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/resume").permitAll()
                        .requestMatchers("/api/transactions/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/banks/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )

                // Make the session stateless (required for JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Add JWT token filter before the default username/password filter
                .addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("Security configuration complete");
        return http.build();
    }
}
