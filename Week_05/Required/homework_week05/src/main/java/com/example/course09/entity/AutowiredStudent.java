package com.example.course09.entity;

import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Data
public class AutowiredStudent implements BeanNameAware, ApplicationContextAware {
    private String beanName;
    private ApplicationContext applicationContext;
    public void printName(){
        System.out.println("我是自动注入进来的bean，我叫AutowiredStudent,beanName=" + beanName);
        System.out.println(applicationContext.getBeanDefinitionNames());
    }
}
