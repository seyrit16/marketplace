package com.example.product_service.exception;

public class FileNotFoundException extends RuntimeException{
    public FileNotFoundException(String message){
        super(message);
    }
}
