package com.haythemlp.springboot;


import com.haythemlp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class ToDoList {




    public static void main(String[] args)

    {
        SpringApplication.run(ToDoList.class,args);
    }
}
