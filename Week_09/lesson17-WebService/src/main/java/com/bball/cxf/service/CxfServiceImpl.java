package com.bball.cxf.service;

public class CxfServiceImpl implements CxfService {
    @Override
    public String sayHello(String name, int age) {
        System.out.println("一个请求进来");
        return "Hello!!!" + name + "(" + age + " years old)";
    }
}
