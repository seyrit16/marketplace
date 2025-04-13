package com.example.user_service.exception;

public class PasswordIsMissingException extends RuntimeException {
    public PasswordIsMissingException(String message) {
        super(message);
    }
}
