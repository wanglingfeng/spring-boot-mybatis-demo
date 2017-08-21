package com.lfwang.demo.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lfwang on 2017/8/21.
 */
public class RedisCache implements Cache {
    
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);
    
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private RedisTemplate redisTemplate;
    
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
    
    public RedisCache(String id) {
        if (null == id) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        
        this.id = id;
    }
    
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object o, Object o1) {

    }

    @Override
    public Object getObject(Object o) {
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
