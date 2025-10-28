package com.rho.srpingsecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    private final PasswordEncoder passwordEncoder;

    public HelloController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/home")
    public String home(){
        return "hello.html";
    }
}
