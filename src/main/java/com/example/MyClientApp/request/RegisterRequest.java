package com.example.MyClientApp.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPas;
}
