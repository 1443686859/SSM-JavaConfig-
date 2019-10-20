package com.huang.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


import javax.sql.DataSource;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:01
 */
@Configuration
public class DataSourcesConfig {
    @Profile("dev")
    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScript("classpath:db/ssm_perfect_db.sql").
                setScriptEncoding("UTF-8").build();
    }


}
