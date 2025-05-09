package com.example.order_service.dto.response.pickup_point;

import com.example.order_service.dto.response.address.AddressResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointForUserResponse {
    private AddressResponse address;
    private String workingHours;
    private String addInfo;
}
