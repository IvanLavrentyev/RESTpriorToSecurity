package com.rest.firstexample.demo.repository;

import com.rest.firstexample.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
