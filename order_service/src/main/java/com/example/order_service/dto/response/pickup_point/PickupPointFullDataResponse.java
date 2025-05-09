package com.example.order_service.dto.response.pickup_point;

import com.example.order_service.dto.response.address.AddressResponse;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointFullDataResponse {
    private String email;
    private Boolean isActive;
    private Boolean isLocked;

    private AddressResponse address;
    private String workingHours;
    private String phoneNumber;
    private String addInfo;
}
