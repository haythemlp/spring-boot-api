package com.haythemlp.springboot.auth;

import com.haythemlp.springboot.errors.NotFoundException;
import com.haythemlp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthService {

@Autowired
    UserRepository userRepository;


public Iterable<User> findAll(){

    try {

        return userRepository.findAll();

    } catch (NoSuchElementException ex){

        throw  new NotFoundException(String.format("not foud"));
    }


}

public  User getById(String id){
    try {

        return userRepository.findById(Integer.parseInt(id)).get();

    } catch (NoSuchElementException ex){

        throw  new NotFoundException(String.format("not foud"));
    }


}



}
