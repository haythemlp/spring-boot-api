package com.springboot.api.repository;



import com.springboot.api.auth.User;
import org.springframework.data.repository.CrudRepository;

public  interface UserRepository extends CrudRepository<User,Integer> {


     User findByEmail(String email);

}
