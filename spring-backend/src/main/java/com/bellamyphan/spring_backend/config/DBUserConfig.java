package com.bellamyphan.spring_backend.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.bellamyphan.spring_backend.dbuser.repository",
        entityManagerFactoryRef = "dbUserEntityManagerFactory",
        transactionManagerRef = "dbUserTransactionManager"
)
public class DBUserConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.dbuser")
    public DataSource dbUserDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean dbUserEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dbUserDataSource())
                .packages("com.bellamyphan.spring_backend.dbuser.entity")
                .persistenceUnit("dbUser")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager dbUserTransactionManager(
            @Qualifier("dbUserEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
