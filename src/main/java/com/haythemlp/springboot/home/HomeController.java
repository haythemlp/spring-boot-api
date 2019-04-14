package com.haythemlp.springboot.home;


import com.haythemlp.springboot.auth.AuthService;
import com.haythemlp.springboot.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
