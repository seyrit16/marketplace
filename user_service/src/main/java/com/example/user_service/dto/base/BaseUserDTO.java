package com.example.user_service.dto.base;

import com.example.user_service.dto.response.user.UserProfileResponse;
import com.example.user_service.dto.response.user.UserResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class BaseUserDTO {
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Электронная почта должна быть валидной")
    private String email;

    @Valid
    private UserProfileResponse userProfile;
}
