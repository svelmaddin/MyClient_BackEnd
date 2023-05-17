package com.security.Securitydemo.controller;


import com.security.Securitydemo.dto.TokenResponseDto;
import com.security.Securitydemo.request.LoginRequest;
import com.security.Securitydemo.request.RegisterRequest;
import com.security.Securitydemo.service.AuthService;
import com.security.Securitydemo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
@RestController
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegisterRequest request) {
        userService.createUser(request);
    }



}