package com.example.order_service.dto.request.pickup_point;

import com.example.order_service.dto.request.address.AddressCreateRequest;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointCreateRequest {
    private UUID id;
    private AddressCreateRequest address;
    private String workingHours;
    private String phoneNumber;
    private String addInfo;
}
