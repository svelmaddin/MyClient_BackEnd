package com.example.MyClientApp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserChangePassword {
    @NotEmpty
    @NotBlank
    public String password;
    @NotEmpty
    @NotBlank
    private String confirmPas;
    public String getPassword() {
        return password;
    }

    public String getConfirmPas() {
        return confirmPas;
    }
}
