package com.example.MyClientApp.model;

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
    @Column(unique = true , nullable = false)
    private String email;
    private String profileImageId;

    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("USER");
}
