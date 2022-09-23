package com.bridge.easyinterview.configs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                getStandardError(request, HttpStatus.METHOD_NOT_ALLOWED, e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        var err = new ValidationError(System.currentTimeMillis(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(), "Invalid Parameters",
                "Request with invalid parameters", request.getRequestURI());

        e.getConstraintViolations().forEach(fieldError -> err.addError(fieldError.getPropertyPath().toString(), fieldError.getMessageTemplate()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private StandardError getStandardError(HttpServletRequest request, HttpStatus unauthorized, String message) {
        return new StandardError(System.currentTimeMillis(), unauthorized.value(),
                unauthorized.getReasonPhrase(), message, request.getRequestURI());
    }

}
