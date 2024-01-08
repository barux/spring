package com.barux.progettoSpring.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions (
            MethodArgumentNotValidException ex
    ) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeExceptions (
            RuntimeException ex
    ) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions (
            ValidationException ex
    ) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtExceptions (
            ExpiredJwtException ex
    ) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedExceptions (
            AccessDeniedException ex
    ) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }
}
