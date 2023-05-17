package com.security.Securitydemo.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    @Email
    @NotEmpty
    @NotBlank
    private String email;
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$" ,message = "Invalid password!")
    private String password;
}
