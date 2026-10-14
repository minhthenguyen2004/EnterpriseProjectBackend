package com.example.EnterpriseProject.validation;

import com.example.EnterpriseProject.payload.output.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<APIResponse> handleDisabledException(DisabledException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new APIResponse(false, "Your account has been disabled"));
    }


    @ExceptionHandler(LockedException.class)
    public ResponseEntity<APIResponse> handleLockedException(LockedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new APIResponse(false, "Your account has been locked. Please contact the admin"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<APIResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new APIResponse(false, "Bad credentials"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<APIResponse> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new APIResponse(false, "Login error: " + ex.getMessage()));
    }


}
