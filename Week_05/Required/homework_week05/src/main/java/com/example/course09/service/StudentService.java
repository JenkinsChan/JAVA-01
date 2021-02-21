package com.example.course09.service;

import com.example.course09.entity.AutowiredStudent;
import com.example.course09.entity.ConstructorStudent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements InitializingBean {
    @Autowired
    private AutowiredStudent autowiredStudent;

    private ConstructorStudent constructorStudent;

    public StudentService(ConstructorStudent constructorStudent){
        this.constructorStudent = constructorStudent;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        constructorStudent.printName();
        autowiredStudent.printName();
    }
}
