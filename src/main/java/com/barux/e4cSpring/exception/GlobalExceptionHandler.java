package com.barux.e4cSpring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions (
            MethodArgumentNotValidException ex
    ) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .error(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                        .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeExceptions (
            RuntimeException ex
    ) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions (
            ValidationException ex
    ) {
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build()
        );
    }
}
