package com.example.MyClientApp.controller;


import com.example.MyClientApp.dto.TokenResponseDto;
import com.example.MyClientApp.request.LoginRequest;
import com.example.MyClientApp.request.RegisterRequest;
import com.example.MyClientApp.service.AuthService;
import com.example.MyClientApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService,  UserService userService) {
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