package com.example.user_service.exception;

public class ConflictResourceByUserException extends RuntimeException{

    public ConflictResourceByUserException(String message){
        super(message);
    }
}
