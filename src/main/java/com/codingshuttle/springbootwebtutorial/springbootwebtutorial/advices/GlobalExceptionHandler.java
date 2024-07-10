package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.advices;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
       ApiError apierror=ApiError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
       return new ResponseEntity<>(apierror,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(Exception exception){
        ApiError apierror=ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return new ResponseEntity<>(apierror,HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInputValidationsErrors(MethodArgumentNotValidException exception){
        List<String> errors=exception.getBindingResult()
                .getAllErrors().stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apierror=ApiError.builder().status(HttpStatus.BAD_REQUEST).message("input validation failed").subErrors(errors).build();
        return new ResponseEntity<>(apierror,HttpStatus.BAD_REQUEST);
    }

}
