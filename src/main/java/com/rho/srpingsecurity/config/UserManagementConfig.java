package com.rho.srpingsecurity.config;


import com.rho.srpingsecurity.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

import static org.springframework.security.core.userdetails.User.*;

@Configuration
public class UserManagementConfig {

    @Bean
    UserDetailsService userDetailsService(DataSource datasource){
//        var user = withUsername("user")
//                .password(passwordEncoder().encode("1234"))
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//        UserDetails u = new User("user", passwordEncoder().encode("1234"), AuthorityUtils.createAuthorityList("read"));
//        List<UserDetails> users = List.of(u);
//        return new InMemoryUserDetailsService(users);

        return new JdbcUserDetailsManager(datasource);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}

