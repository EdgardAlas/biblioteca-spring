package com.ues.edu.sv.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorBadRequest(
        String message,
        HttpStatus statusCode,
        LocalDateTime timestamp,
        Object errors) {
}
