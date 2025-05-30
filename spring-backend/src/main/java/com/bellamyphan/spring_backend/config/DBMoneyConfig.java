package com.bellamyphan.spring_backend.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bellamyphan.spring_backend.dbmoney.repository",
        entityManagerFactoryRef = "dbMoneyEntityManagerFactory",
        transactionManagerRef = "dbMoneyTransactionManager"
)
public class DBMoneyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbmoney")
    public DataSource dbMoneyDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean dbMoneyEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dbMoneyDataSource())
                .packages("com.bellamyphan.spring_backend.dbmoney.entity")
                .persistenceUnit("dbMoney")
                .build();
    }

    @Bean
    public PlatformTransactionManager dbMoneyTransactionManager(
            @Qualifier("dbMoneyEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
