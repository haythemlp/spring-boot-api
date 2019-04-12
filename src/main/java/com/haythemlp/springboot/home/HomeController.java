package com.haythemlp.springboot.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {



   @GetMapping(value = "/")
    public String greeting(){

        return "hello ,welcome to boot spring zzz";
    }




}
