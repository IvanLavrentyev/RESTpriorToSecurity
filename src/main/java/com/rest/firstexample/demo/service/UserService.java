package com.rest.firstexample.demo.service;

import com.rest.firstexample.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUserById(long id);
    void addUser(User user);
    void updateUser(User user);
}
