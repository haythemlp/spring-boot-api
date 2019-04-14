package com.springboot.api.auth;

import com.springboot.api.repository.UserRepository;
import com.springboot.api.security.JwtResponse;
import com.springboot.api.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")

public class AuthController {

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;



    @GetMapping(value = {"", "/"})
    public ResponseEntity<Iterable<User>> login() {

        return new ResponseEntity<>(authService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"", "/{id}"})
    public ResponseEntity<User> getByid(@PathVariable String id) {

        User user = authService.getById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping({"", "/register"})
    public ResponseEntity<String> register(@RequestBody User user) {


      String result = authService.save(user);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }



    @PostMapping({"", "/login"})
    public   ResponseEntity<JwtResponse>  login (@RequestBody SignInRequest signInRequest) {

        final Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);



        UserDetails user=authService.loadUserByUsername(signInRequest.getUsername());

        String token =tokenUtil.generateToken(user);


        JwtResponse jwtResponse =new JwtResponse(token);


        return new ResponseEntity<>(jwtResponse,HttpStatus.OK);


    }




}
