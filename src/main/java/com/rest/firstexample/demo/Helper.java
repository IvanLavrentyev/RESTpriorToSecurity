package com.rest.firstexample.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Helper {

    public static void main(String[] args) {
        String pass = "user";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode(pass).contains("{bcrypt}"));
    }

}
