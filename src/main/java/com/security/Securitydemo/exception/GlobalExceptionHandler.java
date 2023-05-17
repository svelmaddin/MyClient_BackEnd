package com.security.Securitydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtCanNotVerifyException.class)
    public ResponseEntity<?> jwtVerifyException(JwtCanNotVerifyException exception) {
        List<String> detail = new ArrayList<>();
        detail.add(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("JwtVerifyException!", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception) {
        List<String> detail = new ArrayList<>();
        detail.add(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("User not Found!", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> duplicateResourceException(DuplicateResourceException exception) {
        List<String> detail = new ArrayList<>();
        detail.add(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Duplicate  resource ", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<?> loginException(LoginException exception) {
        List<String> detail = new ArrayList<>();
        detail.add(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Login Process exception", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotNullException.class)
    public ResponseEntity<?> notNullException(NotNullException exception) {
        List<String> detail = new ArrayList<>();
        detail.add(exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Field can not be null ", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
