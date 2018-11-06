package com.rest.firstexample.demo.controller;


import com.rest.firstexample.demo.model.Role;
import com.rest.firstexample.demo.model.User;
import com.rest.firstexample.demo.service.UserServiceImpl;
import org.jvnet.hk2.internal.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class MainRestController {

    private UserServiceImpl userService;

    @Autowired
    public MainRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> userList() {
        List<User> users = userService.getAllUsers();


        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping(value = "/users/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
                                        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> addUser(@RequestBody User user){

        List<User> users = userService.getAllUsers();

        if (!user.isUserUnique(users))
            return new ResponseEntity<>(users,HttpStatus.CONFLICT);

        HttpHeaders httpHeaders = new HttpHeaders();

        userService.addUser(user);
        users = userService.getAllUsers();

        return new ResponseEntity<>(users,httpHeaders,HttpStatus.OK);
    }

    @GetMapping(value = "/users/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        User user = userService.getUserById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping(value = "users/delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") long id){
        User user = userService.getUserById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        userService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping (value = "users/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        User usr = userService.getUserById(id);

        String userName = (usr.getName().equals(user.getName())) ? usr.getName() : user.getName();
        String userLogin = (usr.getLogin().equals(user.getLogin())) ? usr.getLogin() : user.getLogin();
        String userPassword = (usr.getPassword().equals(user.getPassword())) ? usr.getPassword() : user.getPassword();

        usr.setName(userName);
        usr.setLogin(userLogin);
        usr.setPassword(userPassword);

        userService.updateUser(usr);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }



}