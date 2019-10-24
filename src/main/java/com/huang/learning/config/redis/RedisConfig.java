package com.huang.learning.config.redis;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/20 11:05
 */
@Component
@EnableCaching
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
        JedisConnectionFactory  jedisConnectionFactory=new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPassword("");
        jedisConnectionFactory.setPort(6789);
        jedisConnectionFactory.setDatabase(2);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory rcf){
        RedisTemplate<String,String> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(rcf);
        return  redisTemplate;
    }
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(redisTemplate);
         cacheManager.setDefaultExpiration(30000);
         return  cacheManager;
    }

}
