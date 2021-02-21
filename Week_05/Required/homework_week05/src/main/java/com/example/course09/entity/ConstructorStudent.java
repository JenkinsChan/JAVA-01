package com.example.course09.entity;

import org.springframework.stereotype.Component;

@Component
public class ConstructorStudent {
    public void printName(){
        System.out.println("我是构造器装配进来的bean，我叫ConstructorStudent");
    }
}
