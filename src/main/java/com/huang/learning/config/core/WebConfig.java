package com.huang.learning.config.core;


import com.huang.learning.config.JWT.JwtFilter;
import com.huang.learning.config.datasource.DataSourcesConfig;
import com.huang.learning.config.datasource.TransactionManager;
import com.huang.learning.config.redis.RedisConfig;
import com.huang.learning.config.shiro.ShiroConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.web.Log4jWebSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/19 11:31
 */

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        context.setInitParameter("spring.profiles.active","dev");
        /*FilterRegistration.Dynamic encodingFilter=context.addFilter("encoding-filter",CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding","UTF-8");
        encodingFilter.setInitParameter("forceEncoding","true");
        encodingFilter.setAsyncSupported(true);
        encodingFilter.addMappingForUrlPatterns(null,true,"/*");
        FilterRegistration.Dynamic jwtFilter =context.addFilter("JwtFilter",JwtFilter.class);
        jwtFilter.setAsyncSupported(true);
        jwtFilter.addMappingForUrlPatterns(null,true,"/*");*/
//       context.setInitParameter("log4jConfiguration", "classpath:log4j2.xml");
        super.onStartup(context);
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {

        return new Class<?>[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 当registerDispatcherServlet完成时自定义registration
     *
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("spring.profiles.active", "dev");
    }

    /**
     * 配置servlet过滤器
     *
     * @return
     */
  /*  @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{characterEncodingFilter(),new JwtFilter()};
    }*/
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        //字符集过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
