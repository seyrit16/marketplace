package com.example.order_service.dto.request.pickup_point;

import com.example.order_service.dto.request.address.AddressCreateRequest;
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
    @NotNull(message = "Идентификатор не должен быть пустым")
    private UUID id;

    @NotNull(message = "Адрес не должен быть пустым")
    @Valid
    private AddressCreateRequest address;

    @NotBlank(message = "Часы работы не должны быть пустыми")
    private String workingHours;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "\\+?[1-9]\\d{1,14}", message = "Номер телефона должен быть валидным")
    private String phoneNumber;

    private String addInfo;
}
