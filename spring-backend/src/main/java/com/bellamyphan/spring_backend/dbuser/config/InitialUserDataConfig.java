package com.bellamyphan.spring_backend.dbuser.config;

import com.bellamyphan.spring_backend.dbuser.service.RoleService;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialUserDataConfig {

    private static final Logger logger = LoggerFactory.getLogger(InitialUserDataConfig.class);

    private final RoleService roleService;
    private final UserService userService;

    @Bean
    public CommandLineRunner initiateUserData() {
        return args -> {
            logger.info("Creating roles...");
            roleService.createFirstRole();

            logger.info("Creating first user...");
            userService.createFirstUser();
        };
    }
}
