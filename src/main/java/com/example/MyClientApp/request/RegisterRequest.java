package com.example.MyClientApp.request;

import com.example.MyClientApp.model.Gender;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
    private String confirmPas;
}
