package com.rest.firstexample.demo.service;

import com.rest.firstexample.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserDetService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.loadUserByName(s);

        if (user == null) throw  new UsernameNotFoundException("user is null");

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),true,
                true,true,
                true, getRoles(user));
    }

    private static Set<GrantedAuthority> getRoles(User user) {
        return user.getRoles().stream().map(role -> "ROLE_".concat(role.getRoleDescription())).
                map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
