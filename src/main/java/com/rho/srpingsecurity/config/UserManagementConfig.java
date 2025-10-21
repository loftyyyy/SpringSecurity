package com.rho.srpingsecurity.config;


import com.rho.srpingsecurity.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

import static org.springframework.security.core.userdetails.User.*;

@Configuration
public class UserManagementConfig {

    @Bean
    UserDetailsService userDetailsService(){
//        var user = withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(user);
        UserDetails u = new User("user", "1234", "read");
        List<UserDetails> users = List.of(u);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

