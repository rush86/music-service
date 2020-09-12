package com.skelton.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value(value = "${database.url}")
    private String dbUrl;

    @Value(value = "${database.username}")
    private String dbUser;

    @Value(value = "${database.password}")
    private String dbPassword;

    @Value(value = "${database.poolSize}")
    private int dbPoolSize;

    @Value(value = "${database.driver-class-name}")
    private String dbDriver;

    @Primary
    @Bean(name = "appDataSource")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName(dbDriver);
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setUsername(dbUser);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setPoolName("appConnectionPool");
        hikariConfig.setMaximumPoolSize(dbPoolSize);

        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = {"appJdbcTemplate"})
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
}