package com.barux.e4cSpring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions (
            MethodArgumentNotValidException ex
    ) {
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
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .error(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
