package com.huang.learning.config.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:58
 */
@Configuration
//@ComponentScan("com.huang.learning.config.datasource")
@ComponentScan(basePackages =
        {"com.huang.learning.service","com.huang.learning.config.core","com.huang.learning.config.JWT","com.huang.learning.config.redis","com.huang.learning.config.shiro",
        "com.huang.learning.util"},
        excludeFilters={
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {MyFilter.class})
})
public class SpringConfig{

}
