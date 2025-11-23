package com.amaibun.voidcatsmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amaibun.voidcatsmarket.exceptions.types.ProblemDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetails> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        StringBuilder detailMessage = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> {
            detailMessage.append("Field '")
                         .append(violation.getPropertyPath())
                         .append("' ")
                         .append(violation.getMessage())
                         .append("; ");
        });

        String errorMessage = detailMessage.length() > 0 
            ? detailMessage.substring(0, detailMessage.length() - 2) 
            : "Validation failed";

        ProblemDetails problemDetails = new ProblemDetails(
                HttpStatus.BAD_REQUEST.value(),
                "Constraint Violation",
                errorMessage,
                request.getRequestURI()
        );

        return new ResponseEntity<>(problemDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetails> handleGeneralException(Exception ex, HttpServletRequest request) {
        ProblemDetails problemDetails = new ProblemDetails(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            ex.getMessage(),
            request.getRequestURI()
        );

        return new ResponseEntity<>(problemDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}