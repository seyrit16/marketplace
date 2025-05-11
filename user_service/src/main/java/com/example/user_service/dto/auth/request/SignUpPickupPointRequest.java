package com.example.user_service.dto.auth.request;

import com.example.user_service.dto.rest.request.pickup_point.PickupPointCreateRequest;
import com.example.user_service.validation.PickupPointPassword;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpPickupPointRequest {
    @NotBlank(message = "Электронная почта не должна быть пустой")
    @Email(message = "Электронная почта должна быть валидной")
    private String email;
    @NotBlank(message = "Пароль не должен быть пустым")
    @PickupPointPassword
    private String password;

    @NotNull(message = "Данные пункта выдачи не должны быть пустыми")
    @Valid
    private PickupPointCreateRequest pickupPointCreateRequest;

    @NotBlank(message = "Код не должен быть пустым")
    private String code;
}

