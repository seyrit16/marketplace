package com.example.user_service.dto.request.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateEmailRequest {
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Электронная почта должна быть валидной")
    private String email;
}
