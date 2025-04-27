package com.example.product_service.exception;

public class InvalidEntityAttributeException extends RuntimeException{
    public InvalidEntityAttributeException(String message){
        super(message);
    }
}
