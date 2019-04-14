package com.haythemlp.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
public class SecuriyConfig extends WebSecurityConfigurerAdapter {


    private  final  String [] END_POINTS ={"/api/auth/**"};

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.cors().and().csrf().disable()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and().authorizeRequests().antMatchers(END_POINTS).permitAll()
               .anyRequest().authenticated().and().httpBasic();
    }
}
