package com.huang.learning.config.shiro;

import com.huang.learning.util.JwtUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
//import org.apache.shiro.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.sql.Time;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ShiroCache<S,V> implements Cache<S,V>{
    private  String shiroCacheExpireTime="600";
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public ShiroCache(RedisTemplate redisTemplate) {
    this.redisTemplate=redisTemplate;
    }

    private String getKey(Object key){
        return "shiro:cache:"+ JwtUtil.getClaim(key.toString(),"account");
    }
    @Override
    public Object get(Object s) throws CacheException {
       return redisTemplate.opsForValue().get(this.getKey(s));
    }

    @Override
    public Object put(Object key, Object value) throws CacheException {
        try{
            redisTemplate.opsForValue().set(this.getKey(key),value,Integer.parseInt(shiroCacheExpireTime), TimeUnit.SECONDS
            );
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
//        return ;
    }

    @Override
    public Object remove(Object s) throws CacheException {
        return redisTemplate.delete(this.getKey(s));
    }

    @Override
    public void clear() throws CacheException {
//        redisTemplate.g
    }

    @Override
    public int size() {
//        return redisTemplate.o;
        return 0;
    }

    @Override
    public Set<S> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
