package com.example.getmesocialservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestrictedInfoException.class)
    public ResponseEntity<String> restrictedInfoError(RestrictedInfoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementError(){
        return new ResponseEntity<> ("No such element found!!!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestrictedNameException.class)
    public ResponseEntity<String> restrictedNameError(RestrictedNameException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
//name (if name is “root” send a custom exception also handle with @ControllerAdvice)

    @ExceptionHandler(InvalidIdTokenException.class)
    public ResponseEntity<String> invalidIdTokenError(InvalidIdTokenException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> illegalArgumentException(){
        return new ResponseEntity<>("Invalid idToken. Please provide a valid idToken!!!", HttpStatus.BAD_REQUEST);
    }
}