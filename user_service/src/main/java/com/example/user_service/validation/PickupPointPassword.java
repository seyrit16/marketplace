package com.example.user_service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SellerPickupPointPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PickupPointPassword {
    String message() default "Пароль должен содержать минимум 10 символов, минимум 2 буквы, из которых хотя бы одна заглавная";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}