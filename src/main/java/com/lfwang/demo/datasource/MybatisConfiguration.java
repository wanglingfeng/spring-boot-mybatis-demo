package com.lfwang.demo.datasource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lfwang on 2017/8/21.
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
public class MybatisConfiguration extends MybatisAutoConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private DataSource writeDataSource;

    @Autowired
    private DataSource readDataSource;

    public MybatisConfiguration(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider, 
                                ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider, 
                                ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        super(properties, interceptorsProvider, resourceLoader, databaseIdProvider, configurationCustomizersProvider);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        log.info("重载父类 sqlSessionFactory init...");
        return super.sqlSessionFactory(routingDataSource());
    }
    
    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        MyAbstractRoutingDataSource dataSource = new MyAbstractRoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
        targetDataSources.put(DataSourceType.read.getType(), readDataSource);
        
        dataSource.setDefaultTargetDataSource(writeDataSource);
        dataSource.setTargetDataSources(targetDataSources);
        
        return dataSource;
    }
}
