package com.huang.learning.config.core;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/18 16:55
 */
@MapperScan(basePackages = "com.huang.learning.dao")
//@Configuration
@Component
public class MybatisConfig {

    //@Value("#{DataSourceConstant.dataUrl}")
    private  String dataUrl="jdbc:h2:mem:hello;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:db/db.sql'";
    //@Value("${spring.datasource.username}")
    private String userName="sa";
    //@Value("${spring.datasource.password}")
    private String password="";
    // @Value("${spring.datasource.driver-class-name}")
    private String className="org.h2.Driver";


    @Bean
    @Scope("singleton")
    public DataSource dataSource(){
        System.out.println("-------------------------------info database---------------");
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setUrl(dataUrl);
        driverManagerDataSource.setPassword(password);
        System.out.println("-----------database end-----------");
        return driverManagerDataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //System.out.println(dataSource().getConnection());
        sessionFactory.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
        //配置pageHelper
        sessionFactory.setPlugins(new Interceptor[]{pageHelper()});
        sessionFactory.setTypeAliasesPackage("com.huang.learning.entity");
        return sessionFactory.getObject();
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mScannerConfigurer = new MapperScannerConfigurer();
        mScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mScannerConfigurer.setBasePackage("com.huang.learning.dao");
        return mScannerConfigurer;
    }
    @Bean("transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
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
