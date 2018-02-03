package com.lfwang.demo.aop;

import com.lfwang.demo.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lfwang on 2017/8/21.
 */
@Aspect
@Component
public class DataSourceAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Before("execution(* com.lfwang.demo.repository.mapper.*.find*(..)) || " +
            "execution(* com.lfwang.demo.repository.mapper.*.get*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
        log.info("dataSource切换到: Read");
    }

    @Before("execution(* com.lfwang.demo.repository.mapper.*.insert*(..)) || " +
            "execution(* com.lfwang.demo.repository.mapper.*.update*(..)) || " +
            "execution(* com.lfwang.demo.repository.mapper.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
        log.info("dataSource切换到: Write");
    }
}
