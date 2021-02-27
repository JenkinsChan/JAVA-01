package com.example.course10;

import com.example.course10.service.BeanDemo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDemoStarter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanDemo.xml");
        BeanDemo beanDemo = context.getBean(BeanDemo.class);
        beanDemo.sayHello();
        context.close();
    }
}
