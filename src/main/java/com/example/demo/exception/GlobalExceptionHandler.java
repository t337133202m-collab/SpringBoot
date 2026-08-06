package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(
            StudentNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), 404);

        return ResponseEntity
                .badRequest()
                .body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationError(
            MethodArgumentNotValidException ex) {

        ErrorResponse error = new ErrorResponse("Validation Failed", 400);

        return ResponseEntity
                .badRequest()
                .body(error);

    }

}