package com.javatechie.redis.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final ExceptionMessage exceptionMessage;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex) {
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }


}
