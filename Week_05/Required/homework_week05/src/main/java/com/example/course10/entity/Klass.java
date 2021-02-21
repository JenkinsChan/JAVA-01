package com.example.course10.entity;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Klass {
    @Autowired
    List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
}
