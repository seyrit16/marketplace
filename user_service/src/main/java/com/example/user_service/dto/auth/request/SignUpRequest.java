package com.example.user_service.dto.auth.request;

import com.example.user_service.dto.base.BaseUserDTO;
import com.example.user_service.validation.UserPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest extends BaseUserDTO {
    @NotBlank(message = "Пароль не должен быть пустым")
    @UserPassword
    private String password;

    @NotBlank(message = "Код подтверждения не должен быть пустым")
    private String verifyCode;
}
