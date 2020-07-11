package com.example.getmesocialservice.exception;

public class InvalidIdTokenException extends Exception{
    @Override
    public String getMessage(){
        return "Invalid idToken. Please provide a valid idToken!!!";
    }

}
