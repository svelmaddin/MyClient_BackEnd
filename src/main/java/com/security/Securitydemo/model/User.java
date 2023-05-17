package com.security.Securitydemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String profileImageId;

    @Enumerated(EnumType.STRING)
    private Role role;
}
