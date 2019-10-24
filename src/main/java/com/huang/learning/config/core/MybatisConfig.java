package com.huang.learning.config.core;

import com.github.pagehelper.PageHelper;
import com.huang.learning.config.datasource.DataSourcesConfig;
import com.huang.learning.config.datasource.TransactionManager;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;


import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:55
 */
@Configuration
//@Profile("dev")
@MapperScan(basePackages = "com.huang.learning.dao")
@Import({DataSourcesConfig.class,TransactionManager.class})
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*Mapping.xml"));
        //配置pageHelper
        sessionFactory.setPlugins(new Interceptor[]{pageHelper()});
        sessionFactory.setTypeAliasesPackage("com.huang.learning.entity");
        return sessionFactory.getObject();
    }

    @Bean
    public PageHelper pageHelper() {
        //logger.info("MyBatisConfiguration.pageHelper()");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
