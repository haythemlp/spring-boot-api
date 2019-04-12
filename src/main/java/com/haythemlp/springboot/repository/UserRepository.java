package com.haythemlp.springboot.repository;



import com.haythemlp.springboot.auth.User;
import org.springframework.data.repository.CrudRepository;

public  interface UserRepository extends CrudRepository<User,Integer> {


}
