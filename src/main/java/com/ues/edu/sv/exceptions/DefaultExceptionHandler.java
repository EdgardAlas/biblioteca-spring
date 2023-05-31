package com.ues.edu.sv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(
                e.getMessage(),
                status,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity
                .status(status)
                .body(apiError);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(Exception e) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                e.getMessage(),
                status,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity
                .status(status)
                .body(apiError);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflcit(Exception e) {

        HttpStatus status = HttpStatus.CONFLICT;

        ApiError apiError = new ApiError(
                e.getMessage(),
                status,
                java.time.LocalDateTime.now()
        );
        return ResponseEntity
                .status(status)
                .body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorBadRequest> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiErrorBadRequest apiError = new ApiErrorBadRequest(
                "Validation errors",
                status,
                java.time.LocalDateTime.now(),
                errors
        );

        return ResponseEntity
                .status(status)
                .body(apiError);

    }

}