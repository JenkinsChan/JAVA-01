package com.example.course10.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

public class BeanDemo implements BeanFactoryPostProcessor, InstantiationAwareBeanPostProcessor, BeanNameAware,
        BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware, InitializingBean,DisposableBean {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("[1]工厂级别，BeanFactoryPostProcessor.postProcessBeanFactory()执行");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("[2]容器级别，InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation()执行,beanName=" + beanName);
        return null;
    }

    public BeanDemo(){
        System.out.println("[3]bean级别，bean构造器执行");
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("[4]容器级别，属性填充时执行，InstantiationAwareBeanPostProcessor.postProcessProperties()");
        return null;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("[5]bean级别，BeanClassLoaderAware.setBeanClassLoader(),classLoader=" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[5]bean级别，BeanFactoryAware.setBeanFactory(),beanFactory=" + beanFactory);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("[5]bean级别，BeanNameAware.setBeanName(),s=" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[6]bean级别，BeanPostProcessor.postProcessBeforeInitialization();\n" +
                "包括ApplicationContextAware.setApplicationContext(),applicationContext=" + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[7]bean级别，InitializingBean.afterPropertiesSet()");
    }

    public void init(){
        System.out.println("[8]bean级别，init-method指定的方法");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[9]bean级别，BeanPostProcessor.postProcessAfterInitialization()");
        return null;
    }

    public void sayHello(){
        System.out.println("[10]bean级别，bean内部方法执行，sayHello");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[11]bean级别，DisposableBean.destroy()");
    }

    public void destroyMethod(){
        System.out.println("[12]bean级别，destroy-method指定的方法执行");
    }
}
