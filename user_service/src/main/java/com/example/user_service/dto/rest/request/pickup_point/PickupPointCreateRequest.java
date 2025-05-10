package com.example.user_service.dto.rest.request.pickup_point;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointCreateRequest {
    private UUID id;
    private PPAddressCreateRequest address;
    private String workingHours;
    private String phoneNumber;
    private String addInfo;
}
