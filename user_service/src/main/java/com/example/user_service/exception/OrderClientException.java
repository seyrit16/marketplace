package com.example.user_service.exception;

public class OrderClientException extends RuntimeException{
    public OrderClientException(String message, Throwable cause){
        super(message);
    }
}
