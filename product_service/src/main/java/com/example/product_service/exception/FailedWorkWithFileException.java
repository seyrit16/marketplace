package com.example.product_service.exception;

public class FailedWorkWithFileException extends RuntimeException{
    public FailedWorkWithFileException(String message){
        super(message);
    }
}
