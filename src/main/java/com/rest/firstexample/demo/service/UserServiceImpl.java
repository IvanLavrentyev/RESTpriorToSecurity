package com.rest.firstexample.demo.service;

import com.rest.firstexample.demo.model.User;
import com.rest.firstexample.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.delete(this.getUserById(id));
    }

    @Override
    public void addUser(User user) {
//        String password = passwordEncoder.encode(user.getPassword());
//        password = password.substring(password.length()/2);
//        user.setPassword(password);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User loadUserByName(String name) {
        return userRepository.loadUserByName(name);
    }
}

