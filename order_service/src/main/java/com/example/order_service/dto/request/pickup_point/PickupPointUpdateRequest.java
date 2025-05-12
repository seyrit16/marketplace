package com.example.order_service.dto.request.pickup_point;

import lombok.*;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointUpdateRequest {
    private String workingHours;

    @Pattern(regexp = "\\+?[1-9]\\d{1,14}", message = "Номер телефона должен быть валидным")
    private String phoneNumber;

    private String addInfo;
}
