package com.example.course10.dao;

import com.example.course10.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertOne(Emp emp){
        String sql = "insert into emp(id, name, age) values(?,?,?)";
        jdbcTemplate.update(sql, emp.getId(),emp.getName(), emp.getAge());
    }
}
