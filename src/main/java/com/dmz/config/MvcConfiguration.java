package com.dmz.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.dmz.converter.String2DateConverter;
import com.dmz.interceptor.CustomInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 实现这个WebMvcConfigurer接口 → springmvc的组件配置
 */
@EnableWebMvc

@ComponentScan("com.dmz.controller")//容器分家
public class MvcConfiguration implements WebMvcConfigurer {

    //静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //mapping → addResourceHandler
        //location → addResourceLocations
        registry.addResourceHandler("/pic1/**").addResourceLocations("file:/Users/dumingze/Desktop/MySpringMVC/");//文件路径
        registry.addResourceHandler("/pic2/**").addResourceLocations("classpath:/"); //classpath:java或resources
        registry.addResourceHandler("/html/**").addResourceLocations("/"); //webroot → webapp
    }


    //interceptor的配置 在handler真正处理前后调用
    /*    <mvc:interceptors>
          <!--全局-->
          <!--<ref bean="customInterceptor"/>
          <bean class="com.cskaoyan.interceptor.CustomInterceptor"/>-->
          <mvc:interceptor>
            <!--path中填的是url-->
            <!--/hello 只针对hello请求-->
            <!--/hello* 针对helloxxx请求-->
            <!--/hello/* 针对hello/xxx一级任意目录的请求-->
            <!--/hello/** 针对/hello/多级任意目录的请求-->
            <mvc:mapping path="/hello/**"/>
            <ref bean="customInterceptor"/>
          </mvc:interceptor>
         </mvc:interceptors>
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //interceptor配置：addInterceptor
        //作用范围：addPathPatterns
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/pic*/**");
    }

    //Spring上传文件的时候需要用的，方法名不能变
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }


/*
    //spring使用字符串返回modleAndView的时候，添加前缀和后缀
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }*/

    //国际化需要的，方法名不能变 ，得到请求的语言标识，并设置默认请求语言类型
    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName("language");
        //设置默认中文
        cookieLocaleResolver.setDefaultLocale(Locale.UK);
        return cookieLocaleResolver;
    }
    //国际化相关，设置内容集合
    @Bean
    public ReloadableResourceBundleMessageSource messageSource1(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:message");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }

    @Autowired
    ReloadableResourceBundleMessageSource messageSource1;
    public Validator localValidatorFactoryBean(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.setValidationMessageSource(messageSource1);

        return localValidatorFactoryBean;
    }
    @Override
    public Validator getValidator() {
        Validator validator = localValidatorFactoryBean();
        return validator;
    }

    //converter   外科手术,自行对参数进行转换处理
    @Autowired
    ConfigurableConversionService conversionService;

    @PostConstruct
    public void addConverters(){
        conversionService.addConverter(new String2DateConverter());
    }
    @Bean
    @Primary
    public ConfigurableConversionService conversionService(){
        return conversionService;
    }


}
