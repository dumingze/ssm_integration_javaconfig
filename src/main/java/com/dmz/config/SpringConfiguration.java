package com.dmz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.dmz",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class, EnableWebMvc.class}))
// 容器分家 EnableWebMvc.class的意思是，让MvcConfiguration也不接受SpringConfiguration的管理，因为com.cskaoyan中包含MvcConfiguration
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringConfiguration {

//---------------------------------------------------------------mybatis

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/j23_db?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("zrw0714");
        return dataSource;
    }

    //sqlSessionFactory
    //因为SqlSessionFactoryBean的形参的dataSource是
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //sqlSessionFactoryBean.setTypeHandlersPackage();
        //sqlSessionFactoryBean.setTypeAliasesPackage();
        sqlSessionFactoryBean.setTypeHandlersPackage("com.dmz.typehandler");
        return sqlSessionFactoryBean;
    }

    //MapperScannerConfigurer
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //这里，之所以方法的参数里没有SqlSessionFactoryBean sqlSessionFactory，是因为下面的方法里需要的是一个字符串，
        //不是对象的引用，跟之前的有点不一样
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.dmz.mapper");
        return mapperScannerConfigurer;
    }


    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
