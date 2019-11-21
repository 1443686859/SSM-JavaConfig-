package com.huang.learning.config.shiro;


import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiroCacheManager implements CacheManager {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public ShiroCacheManager(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }
    @Override
    public <K, V> Cache<K,V> getCache(String name) {
        return new ShiroCache<K, V>(redisTemplate);
    }


}
