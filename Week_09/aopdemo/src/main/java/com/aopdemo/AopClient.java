package com.aopdemo;

public class AopClient {
    public static void main(String[] args) {
        UserService userService = AopProxy.create(UserService.class);
        System.out.println(userService.sayHello());
    }
}
