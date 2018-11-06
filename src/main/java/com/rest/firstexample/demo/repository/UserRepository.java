package com.rest.firstexample.demo.repository;

import com.rest.firstexample.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
