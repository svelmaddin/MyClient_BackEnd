package com.security.Securitydemo.exception;

public class JwtCanNotVerifyException extends RuntimeException{
    public JwtCanNotVerifyException(String message) {
        super(message);
    }
}
