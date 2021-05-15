package com.plaidcamp.mealogram.common.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    // MYSQL
    @Bean(name="mysqlSource")
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    public DataSource mysqlDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="mysqlJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlSource") DataSource dsMysql) {
        return new JdbcTemplate(dsMysql);
    }

    // Postgre

    // Oracle

    //NoSQL...and so on...
}
