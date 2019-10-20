package com.huang.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ConfigurableWebEnvironment;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import java.lang.annotation.Annotation;

/**
 * @author huangkuanyi huang_kuan_yi@163.com
 * @date 2019/10/19 11:31
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{


    @Override
    protected Class<?>[] getRootConfigClasses() {
        WebApplicationContext servletContext = super.createServletApplicationContext();
        ConfigurableWebEnvironment configurableWebEnvironment= (ConfigurableWebEnvironment) servletContext.getEnvironment();
        configurableWebEnvironment.setActiveProfiles("dev");
        return new Class<?>[]{SpringConfig.class, MybatisConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
