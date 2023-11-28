package com.softtek.testjava2020.controller;

import com.softtek.testjava2020.domain.exception.PriceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class PriceControllerExceptionHandler {
    @ExceptionHandler(PriceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handlePriceNotFoundException(RuntimeException ex) {

        Map<String, String> errorMessage = Map.ofEntries(
                Map.entry("error_message", ex.getMessage()),
                Map.entry("status", HttpStatus.NOT_FOUND.toString())
        );
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
