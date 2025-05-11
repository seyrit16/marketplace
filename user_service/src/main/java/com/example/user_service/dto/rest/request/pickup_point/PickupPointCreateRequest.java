package com.example.user_service.dto.rest.request.pickup_point;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointCreateRequest {
    private UUID id;
    @NotNull(message = "Адрес не должен быть пустым")
    @Valid
    private PPAddressCreateRequest address;
    @NotBlank(message = "Часы работы не должны быть пустыми")
    private String workingHours;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "\\+?[1-9]\\d{1,14}", message = "Номер телефона должен быть валидным")
    private String phoneNumber;

    private String addInfo;
}
