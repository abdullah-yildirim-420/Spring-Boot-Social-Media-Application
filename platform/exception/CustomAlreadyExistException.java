package com.example.temel.exception;

public class CustomAlreadyExistException extends RuntimeException{

    public CustomAlreadyExistException(String message){
        super(message);
    }

}
