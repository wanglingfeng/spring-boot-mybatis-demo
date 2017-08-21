package com.lfwang.demo.datasource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by lfwang on 2017/8/21.
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {
    
    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        
        if (StringUtils.isBlank(typeKey)) return DataSourceType.write.getType();
        
        return typeKey;
    }
}
