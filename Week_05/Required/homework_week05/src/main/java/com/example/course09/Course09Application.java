package com.example.course09;

import com.example.course09.config.EntityConfig;
import com.example.course09.entity.ImportStudent;
import com.example.course09.entity.NewStudent;
import com.example.course09.entity.XMLStudent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Course09Application {

    public static void main(String[] args) {
        /**
         * 5种方式装配bean
         */
        // 第一，使用XML文件装配
        xmlBean();

        // 第二，直接new对象装配，第三import方式装配
        newAndImportBean();

        // 第四和第五是使用自动装配和构造器方式装配，对应类是StudentService，AutowiredStudent，ConstructorStudent
        SpringApplication.run(Course09Application.class, args);
    }


    /**
     * XML配置文件方式装配bean
     */
    private static void xmlBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        XMLStudent xmlStudent = context.getBean(XMLStudent.class);
        xmlStudent.printName();
    }

    /**
     * new对象和import方式装配bean
     */
    private static void newAndImportBean(){
        // new对象方式装配bean
        ApplicationContext context = new AnnotationConfigApplicationContext(EntityConfig.class);
        NewStudent newStudent = context.getBean(NewStudent.class);
        newStudent.printName();

        // import方式装配bean
        ImportStudent importStudent = context.getBean(ImportStudent.class);
        importStudent.printName();
    }
}
