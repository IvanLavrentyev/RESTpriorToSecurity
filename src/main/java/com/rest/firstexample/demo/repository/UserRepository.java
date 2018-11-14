package com.rest.firstexample.demo.repository;


import com.rest.firstexample.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query ("select u from User u where u.login =?1")
    User loadUserByName(String name);
}
