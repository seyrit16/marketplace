package com.example.user_service.exception;

public class SellerClientException extends RuntimeException{
    public SellerClientException(String message, Throwable cause){
        super(message,cause);
    }
}
