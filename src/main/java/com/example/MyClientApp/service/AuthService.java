package com.example.MyClientApp.service;

import com.example.MyClientApp.dto.TokenResponseDto;
import com.example.MyClientApp.exception.CustomException;
import com.example.MyClientApp.request.CreateShopRequest;
import com.example.MyClientApp.request.LoginRequest;
import com.example.MyClientApp.request.RegisterRequest;
import com.example.MyClientApp.util.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import static com.example.MyClientApp.util.ErrorMessage.WRONG_USER_DETAIL;

@Service
public class AuthService {
    private final UserService userService;
    private final StoreService storeService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService,
                       StoreService storeService, TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.storeService = storeService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public static String getLoggedInUsername() {
        return ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(auth))
                    .userDto(userService.getUser(loginRequest.getEmail()))
                    .build();
        } catch (Exception e) {
            throw new CustomException(WRONG_USER_DETAIL, " ");
        }
    }

    public TokenResponseDto register(RegisterRequest request) {
        userService.createUser(request);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                .userDto(userService.getUser(request.getEmail()))
                .build();
    }

    public TokenResponseDto registerStore(CreateShopRequest request) {
        storeService.createShop(request);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                .userDto(userService.getUser(request.getEmail()))
                .build();
    }

}
