package com.bellamyphan.spring_backend.config;

import com.bellamyphan.spring_backend.dbmoney.service.BankTypeService;
import com.bellamyphan.spring_backend.dbmoney.service.TransactionTypeService;
import com.bellamyphan.spring_backend.dbuser.entity.User;
import com.bellamyphan.spring_backend.dbuser.service.RoleService;
import com.bellamyphan.spring_backend.dbuser.service.UserDemoService;
import com.bellamyphan.spring_backend.dbuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialAppDataConfig {

    private static final Logger logger = LoggerFactory.getLogger(InitialAppDataConfig.class);
    private final BankTypeService bankTypeService;
    private final TransactionTypeService transactionTypeService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserDemoService userDemoService;

    @Bean
    public CommandLineRunner initAppData() {
        return (args) -> {
            initializeMoneyDbData();
            initializeUserDbData();
        };
    }

    private void initializeMoneyDbData() {
        logger.info("Creating bank types...");
        bankTypeService.createFirstBankType();

        logger.info("Creating transaction types...");
        transactionTypeService.createFirstTransactionType();
    }

    private void initializeUserDbData() {
        logger.info("Creating user roles...");
        roleService.initializeRoles();

        logger.info("Creating first admin user...");
        userService.createFirstAdminUser();

        logger.info("Creating first demo user...");
        User demoUser = userService.createFirstDemoUser();
        // Generate demo data for the created user
        userDemoService.generateDemoData(demoUser);
    }
}
