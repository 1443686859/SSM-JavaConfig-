package com.huang.learning.config.core;

import com.huang.learning.config.redis.RedisConfig;
import com.huang.learning.config.shiro.ShiroConfig;
import com.huang.learning.config.datasource.TransactionManager;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:58
 */
@Configuration
@EnableTransactionManagement
@Import({RedisConfig.class, ShiroConfig.class, TransactionManager.class}
)
@ComponentScan(basePackages = {"com.huang.learning.service"})
public class SpringConfig {

}
