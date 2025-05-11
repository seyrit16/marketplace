package com.example.order_service.exception;

public class CartServiceWorkException extends RuntimeException{
    public CartServiceWorkException(String message){
        super(message);
    }
}
