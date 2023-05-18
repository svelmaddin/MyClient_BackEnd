package com.example.MyClientApp.service;


import com.example.MyClientApp.exception.CustomException;
import com.example.MyClientApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import static com.example.MyClientApp.util.ErrorMessage.*;


@Service
public class ValidationService {
    private final UserRepository userRepository;

    public ValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    protected void emailCheck(String email) {
        if (userRepository.existsUserByEmail(email)) {
            throw new CustomException(TAKEN_EMAIL, "email");
        }
        if (email.isEmpty()) {
            throw new CustomException(EMAIL_NOT_NULL, "email");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new CustomException(INVALID_EMAIL, "email");
        }
    }

    protected void passwordCheck(String password) {
        if (password.isEmpty()) {
            throw new CustomException(PASSWORD_NOT_NULL, "password");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new CustomException(INVALID_PASSWORD, "password");
        }
    }

    protected void usernameCheck(String username) {
        if (userRepository.existsUserByUsername(username)) {
            throw new CustomException(TAKEN_USERNAME, "username");
        }
    }

}
