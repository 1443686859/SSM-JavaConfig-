package com.huang.learning.config.JWT;

import com.huang.learning.config.redis.RedisClient;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hky
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Value("${Jwt.refreshToken-expireTime}")
    private  String refreshTokenExpireTime;
    @Autowired
   private RedisClient redis;
}
