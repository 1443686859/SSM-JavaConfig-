package com.huang.learning.config.redis;


/*import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper*/import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

;


/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/20 11:05
 */

//@Componen
@Configuration
@PropertySource(value = "classpath:redis.properties",encoding = "UTF-8")
public class RedisConfig extends CachingConfigurerSupport {

//    @Value("${redis.host}")
    private String host="127.0.0.1";
    //@Value("${redis.port}")
    private int port=6379;
    //@Value("${redis.password}")
    private String password="";
    //@Value("${redis.maxIdle}")
    private int  maxIdle=400;
    //@Value("${redis.maxTotal}")
    private int maxTotal=6000;
   // @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis=1000;
    //@Value("${redis.testOnBorrow}")
    private boolean testOnBorrow=true;
    //@Value("${redis.blockWhenExhausted}")
    private boolean blockWhenExhausted=true;
//    @Value("${redis.timeout}")
    private int timeout=100000;
//    @Value("${redis.expiration}")
    private int defaultExpireTime=36000;


    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        System.out.println(toString());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.valueOf(maxTotal));
        jedisPoolConfig.setMaxIdle(Integer.valueOf(maxIdle));
        jedisPoolConfig.setMaxWaitMillis(Integer.valueOf(maxWaitMillis));;
        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        jedisPoolConfig.setBlockWhenExhausted(Boolean.valueOf(blockWhenExhausted));
//        jedisPoolConfig.setTime
        return jedisPoolConfig;
    }
    /**
     * JedisConnectionFactory
     */
    @Bean(name="jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        //System.out.println(toString());
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(Integer.valueOf(port));
        jedisConnectionFactory.setTimeout(Integer.valueOf(timeout));
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return jedisConnectionFactory;
    }

    /**
     * redisTemplate
     */
//	@Bean(name="redisTemplate")
//	public StringRedisTemplate redisTemplate() {
//		StringRedisTemplate redisTemplate = new StringRedisTemplate();
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//		return redisTemplate;
//	}

    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置String类型的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 设置Hash类型的序列化规则和 key的序列化规则
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", maxIdle='" + maxIdle + '\'' +
                ", maxTotal='" + maxTotal + '\'' +
                ", maxWaitMillis='" + maxWaitMillis + '\'' +
                ", testOnBorrow='" + testOnBorrow + '\'' +
                ", blockWhenExhausted='" + blockWhenExhausted + '\'' +
                ", timeout='" + timeout + '\'' +
                ", defaultExpireTime='" + defaultExpireTime + '\'' +
                '}';
    }
}
