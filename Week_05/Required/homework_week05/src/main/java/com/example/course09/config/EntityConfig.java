package com.example.course09.config;

import com.example.course09.entity.ImportStudent;
import com.example.course09.entity.NewStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ImportStudent.class)
public class EntityConfig {
    @Bean(initMethod = "initMethod")
    public NewStudent getStudent(){
        return new NewStudent();
    }
}
