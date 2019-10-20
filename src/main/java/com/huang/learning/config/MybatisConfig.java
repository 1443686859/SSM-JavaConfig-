package com.huang.learning.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:55
 */
@Configuration
@Profile("dev")
@Import(DataSourcesConfig.class)
public class MybatisConfig {

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //获取数据源
        DataSource dataSource =new DataSourcesConfig().dataSource();
        // 设置MyBatis 配置文件的路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 设置mapper 对应的XML 文件的路径
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mappers/**.xml"));
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.huang.learning.entity");

        return sqlSessionFactoryBean;
    }

}
