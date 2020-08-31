package com.dmz.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 容器中注册这个组件 → 哪一些组件会经过下面的before和after方法
 *                      → 除了他本身，所有的其他组件生命周期都会经过这个过程
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("6、BeanPostProcessor的before" + ",beanName为" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("9、BeanPostProcessor的after");
        return bean;
    }
}
