package com.example.course10.entity;

import com.example.course10.aop.ISchool;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Data
@Component
public class School implements ISchool, InitializingBean {
    @Autowired(required = true) //primary
    private Klass class1;
    
    @Resource(name = "student100")
    Student student100;
    
    @Override
    @PostConstruct
    public void ding(){
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化School");
    }
}
