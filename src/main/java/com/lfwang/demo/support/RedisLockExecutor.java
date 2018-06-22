package com.lfwang.demo.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author keleguo
 * @date Created in 2018/6/22
 */
@Component
public class RedisLockExecutor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 设置锁
     *
     * @param key
     * @param timeout 单位秒
     * @return
     */
    public boolean tryLock(String key, long timeout) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // 设置过期时间
        Long expireTime = System.currentTimeMillis() + timeout * 1000;
        String strExpireTime = String.valueOf(expireTime);

        boolean lockResult = valueOperations.setIfAbsent(key, strExpireTime);
        if (lockResult) {
            // 获取锁成功
            return true;
        }

        try {
            // 获取旧的过期时间
            Long oldExpireTime = Long.valueOf(valueOperations.get(key));
            if (System.currentTimeMillis() < oldExpireTime) {
                return false;
            }
        } catch (NumberFormatException e) {
            redisTemplate.delete(key);
            lockResult = valueOperations.setIfAbsent(key, strExpireTime);
            return lockResult;
        }

        // 设置新的过期时间
        String getSetResult = valueOperations.getAndSet(key, strExpireTime);
        // 如果相等，锁获取成功；如果失败，锁已被别的请求获取走了
        return getSetResult.equals(strExpireTime);
    }

    public void removeLock(String key) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }
}
