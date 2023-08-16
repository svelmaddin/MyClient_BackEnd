package com.example.MyClientApp.controller;


import com.example.MyClientApp.dto.TokenResponseDto;
import com.example.MyClientApp.request.CreateShopRequest;
import com.example.MyClientApp.request.LoginRequest;
import com.example.MyClientApp.request.RegisterRequest;
import com.example.MyClientApp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/register/shops")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponseDto> registerShop(@RequestBody CreateShopRequest request) {
        return ResponseEntity.ok(authService.registerStore(request));
    }
}