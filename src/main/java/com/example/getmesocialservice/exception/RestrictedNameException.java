package com.example.getmesocialservice.exception;

public class RestrictedNameException extends Exception {
    @Override
    public String getMessage(){
        return "Name cannot be root!!!";
    }
}
