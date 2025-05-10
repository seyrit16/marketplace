package com.example.order_service.dto.response.pickup_point;

import com.example.order_service.dto.response.address.AddressResponse;
import com.example.order_service.dto.rest.response.UserResponse;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointFullDataResponse {
    private UserResponse userInfo;

    private AddressResponse address;
    private String workingHours;
    private String phoneNumber;
    private String addInfo;
}
