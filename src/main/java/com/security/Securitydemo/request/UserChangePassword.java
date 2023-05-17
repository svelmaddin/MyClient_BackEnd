package com.security.Securitydemo.request;

import jakarta.validation.constraints.NotBlank;

public class UserChangePassword {

    @NotBlank
    public String password;
    public String getPassword() {
        return password;
    }
}
