package com.example.temel.util;

import com.example.temel.exception.CustomInvalidException;
import com.example.temel.exception.CustomNotFoundException;
import com.example.temel.exception.SameValueException;

public class ValidationUtil {

    public static void validateMatch(String storedPassword, String providedPassword, String message){
        if (!storedPassword.equals(providedPassword)){
            throw new CustomInvalidException(message);
        }
    }

    public static void validateExistence(Long storedId, Long providedId, String message){
        if(!storedId.equals(providedId)){
            throw new CustomNotFoundException(message);
        }
    }

    public static void validateNotSame(String stored, String update, String message){
        if(stored.equals(update)){
            throw new SameValueException(message);
        }
    }


}
