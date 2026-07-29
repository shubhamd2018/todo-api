package com.shubham.todoapi.exception;

import com.shubham.todoapi.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFound(TodoNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {

        String message = exception
                .getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                message
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
