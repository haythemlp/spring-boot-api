package com.springboot.api.home;


import com.springboot.api.auth.AuthService;
import com.springboot.api.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @Autowired
    AuthService authService;


   @GetMapping(value = "/")
    public  ResponseEntity<Iterable<User>> greeting(){


       return new ResponseEntity<>(authService.findAll(), HttpStatus.OK);
    }




}
