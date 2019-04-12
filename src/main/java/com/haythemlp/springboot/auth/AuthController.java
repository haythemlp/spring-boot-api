package com.haythemlp.springboot.auth;

import com.haythemlp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth")

public class AuthController {

@Autowired
AuthService authService;


    @Autowired
    UserRepository userRepository;



@GetMapping(value = {"","/"})
    public ResponseEntity<Iterable<User> > login() {

    return new ResponseEntity<>( authService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping(value = {"","/{id}"})
    public ResponseEntity<User> getByid(@PathVariable String id) {

    User user= authService.getById(id);

          return new ResponseEntity<>( user, HttpStatus.OK) ;
    }

}
