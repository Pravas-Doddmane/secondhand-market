package com.secondhandmarketplace.secondhandmarketplace.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        String msg = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (msg != null) {
            if (msg.contains("Not your")) status = HttpStatus.FORBIDDEN;
            else if (msg.contains("Invalid credentials")) status = HttpStatus.UNAUTHORIZED;
            else if (msg.contains("already registered")) status = HttpStatus.CONFLICT;
        }

        return ResponseEntity.status(status).body(Map.of("error", msg));
    }
}
