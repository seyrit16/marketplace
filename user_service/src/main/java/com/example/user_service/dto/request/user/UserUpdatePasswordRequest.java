package com.example.user_service.dto.request.user;

import com.example.user_service.validation.UserPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdatePasswordRequest {
    @NotBlank(message = "Пароль не должен быть пустым")
    @UserPassword
    String password;
}
