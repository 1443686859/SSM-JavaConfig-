package com.huang.learning.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


import javax.sql.DataSource;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:01
 */
@Configuration
@Slf4j
@PropertySource( value="classpath:h2DataBase.properties")
@Scope("singleton")
public class DataSourcesConfig {

    @Value("${spring.datasource.url}")
    private  String dataUrl;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String className;
    @Profile("dev")
    @Bean(name = "dataSource")
    public DataSource dataSource(){
        System.out.println("-------------------------------info database---------------");
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setUrl(dataUrl);
        driverManagerDataSource.setPassword(password);
        return driverManagerDataSource;
    }


}
