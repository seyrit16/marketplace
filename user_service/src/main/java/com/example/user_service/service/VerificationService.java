package com.example.user_service.service;

public interface VerificationService {
    void saveVerificationCode(String email, String code);

    Boolean verifyCode(String email, String code);

    void deleteCode(String email);
}
