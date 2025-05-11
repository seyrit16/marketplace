package com.example.user_service.dto.auth.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithCodeRequest {
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Электронная почта должна быть валидной")
    private String email;

    @NotBlank(message = "Код не должен быть пустым")
    @Size(min = 6, max = 6, message = "Код должен быть длиной 6 символов")
    private String code;
}
