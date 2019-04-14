package com.haythemlp.springboot.auth;

import com.haythemlp.springboot.errors.NotFoundException;
import com.haythemlp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    public Iterable<User> findAll() {

        try {

            return userRepository.findAll();

        } catch (NoSuchElementException ex) {

            throw new NotFoundException(String.format("not foud"));
        }


    }

    public User getById(String id) {
        try {

            return userRepository.findById(Integer.parseInt(id)).get();

        } catch (NoSuchElementException ex) {

            throw new NotFoundException(String.format("not foud"));
        }


    }





    public User save(User user) {

        user.setPassword(passwordEncoder().encode(user.getPassword()));

        user.setRole("normal");
        return this.userRepository.save(user);

        // return  user;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // return new org.springframework.security.core.userdetails.User("haythemlp", "1234567", AuthorityUtils.NO_AUTHORITIES);


        User user=userRepository.findByEmail(username);
        if (user ==null){

            throw  new NotFoundException("user not found")
        }

        return  user;
    }



}
