package com.example.MyClientApp.exception;

public class CustomException extends RuntimeException{
    private final String message;
    private final String field;

    public CustomException(String message, String field) {
        this.message = message;
        this.field = field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
