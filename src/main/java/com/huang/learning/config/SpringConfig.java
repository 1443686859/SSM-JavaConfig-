package com.huang.learning.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:58
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.huang.learning.service"})
public class SpringConfig {

}
