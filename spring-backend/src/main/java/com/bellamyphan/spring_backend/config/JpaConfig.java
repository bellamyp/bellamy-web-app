package com.bellamyphan.spring_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    // This bean will read JPA properties from application.properties file.
    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public Map<String, Object> jpaProperties() {
        return new HashMap<>();
    }
}
