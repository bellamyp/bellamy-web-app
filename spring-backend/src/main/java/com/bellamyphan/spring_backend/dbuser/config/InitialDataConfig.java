package com.bellamyphan.spring_backend.dbuser.config;

import com.bellamyphan.spring_backend.dbuser.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialDataConfig {

    private final RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(InitialDataConfig.class);

    @Bean
    public CommandLineRunner initiateData() {
        return args -> {
            logger.info("Creating roles...");
            roleService.createFirstRole();
        };
    }
}
