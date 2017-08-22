package com.lfwang.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lfwang on 2017/8/21.
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisRoutingConfiguration {

    private final MybatisProperties properties;
    
    public MybatisRoutingConfiguration(MybatisProperties properties) {
        this.properties = properties;
    }

    /**
     * 配置路由数据源
     */
    @Bean
    public AbstractRoutingDataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource, 
                                                       @Qualifier("readDataSource") DataSource readDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceContextHolder.Type.write, writeDataSource);
        targetDataSources.put(DataSourceContextHolder.Type.read, readDataSource);

        MyBatisRoutingDataSource dataSource = new MyBatisRoutingDataSource();
        dataSource.setDefaultTargetDataSource(writeDataSource);
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("routingDataSource") AbstractRoutingDataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource); // 指定数据源
        
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("routingDataSource") AbstractRoutingDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static class MyBatisRoutingDataSource extends AbstractRoutingDataSource {

        @Override
        protected Object determineCurrentLookupKey() {
            // 将返回一个枚举，该枚举将与配置文件中的相应枚举进行匹配以定位数据源并切换至指定的数据源
            return DataSourceContextHolder.getType();
        }
    }
}
