package com.example.MyClientApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", Map.of(
                "message", ex.getMessage(),
                "field", ex.getField()
        ));

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorBody);
    }
}
