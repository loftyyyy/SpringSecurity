package com.rho.srpingsecurity.model;


import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    private String authority;


}
