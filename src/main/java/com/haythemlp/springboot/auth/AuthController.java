package com.haythemlp.springboot.auth;

import com.haythemlp.springboot.repository.UserRepository;
import com.haythemlp.springboot.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/auth")

public class AuthController {

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;


    @GetMapping(value = {"", "/"})
    public ResponseEntity<Iterable<User>> login() {

        return new ResponseEntity<>(authService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"", "/{id}"})
    public ResponseEntity<User> getByid(@PathVariable String id) {

        User user = authService.getById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping({"", "/signin"})
    public ResponseEntity<User> signin(@RequestBody User user) {



        User result = authService.save(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }




}
