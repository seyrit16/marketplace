package com.example.user_service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SellerPickupPointPasswordValidator implements ConstraintValidator<SellerPassword, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        // Minimum 10 characters
        if (password.length() < 10) {
            return false;
        }
        // At least 2 letters
        long letterCount = password.chars()
                .filter(Character::isLetter)
                .count();
        if (letterCount < 2) {
            return false;
        }
        // At least one uppercase letter
        return password.chars()
                .anyMatch(Character::isUpperCase);
    }
}