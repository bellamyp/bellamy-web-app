package com.bellamyphan.spring_backend.dbmoney.config;

import com.bellamyphan.spring_backend.dbmoney.service.BankTypeService;
import com.bellamyphan.spring_backend.dbmoney.service.TransactionTypeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialMoneyDataConfig {

    private static final Logger logger = LoggerFactory.getLogger(InitialMoneyDataConfig.class);
    private final BankTypeService bankTypeService;
    private final TransactionTypeService transactionTypeService;

    @Bean
    public CommandLineRunner initiateMoneyData() {
        return (args) -> {
            logger.info("Creating bank types...");
            bankTypeService.createFirstBankType();

            logger.info("Creating transaction types...");
            transactionTypeService.createFirstTransactionType();
        };
    }
}
