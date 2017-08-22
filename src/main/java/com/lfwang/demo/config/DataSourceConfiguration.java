package com.lfwang.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by lfwang on 2017/8/21.
 */
@Configuration
public class DataSourceConfiguration {
    
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    
    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource writeDataSource() {
        log.info("writeDataSource init...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource readDataSource() {
        log.info("readDataSource init...");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
}
