package com.rest.firstexample.demo.securityConfig;

import com.rest.firstexample.demo.service.UserDetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String REALM = "TEST";
    private final static String LOGIN_URL = "http://localhost:8081/login";

    @Autowired
    UserDetService userDetService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests().
                antMatchers("/users", "/user", "/auth").hasRole("ADMIN").
                antMatchers("/user", "/auth").hasRole("USER").
                antMatchers("/auth").permitAll().
                and().
                formLogin().loginPage(LOGIN_URL).
                and().
                httpBasic().realmName(REALM).
                authenticationEntryPoint(getBasicEntryPoint()).
                and().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return  (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}