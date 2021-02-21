package com.example.course10.service;

import com.example.course10.dao.EmpDao;
import com.example.course10.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class EmpService {
    @Autowired
    private EmpDao empDao;

    @PostConstruct
    public void init(){
        System.out.println("操作emp");
        Emp emp = Emp.builder().id(1).name("bball").age(30).build();
        empDao.insertOne(emp);
    }
}
