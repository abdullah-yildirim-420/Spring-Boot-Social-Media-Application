package com.example.temel.exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String message){
        super(message);
    }

    public CustomNotFoundException(String attribute,Long id){
        super(attribute+" with ID "+id+" not found.");
    }
    public CustomNotFoundException(String attribute,String username){
        super(attribute+" with username "+username+" not found.");
    }


}
