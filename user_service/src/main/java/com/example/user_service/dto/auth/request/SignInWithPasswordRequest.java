package com.example.user_service.dto.auth.request;

import com.example.user_service.validation.UserPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithPasswordRequest {
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Электронная почта должна быть валидной")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @UserPassword
    private String password;
}
