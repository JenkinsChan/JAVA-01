package com.example.course10.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component("student100")
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {
    private int id;
    private String name;
    private String beanName;
    private ApplicationContext applicationContext;
    
    public void init(){
        System.out.println("hello...........");
    }
    
//    public Student create(){
//        return new Student(101,"KK101");
//    }

    public void print() {
        System.out.println(this.beanName);
        System.out.println("   context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));

    }
}
