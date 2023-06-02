package com.example.MyClientApp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserChangePassword {
    public String password;
    private String confirmPas;
    public String getPassword() {
        return password;
    }

    public String getConfirmPas() {
        return confirmPas;
    }
}
