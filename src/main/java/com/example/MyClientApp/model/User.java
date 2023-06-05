package com.example.MyClientApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private String surname;
    private String username;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Lob
    @Column(length = 10485760)
    private byte[] profilePhoto;

    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("USER");

}
