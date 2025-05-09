package com.example.order_service.dto.request.pickup_point;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PickupPointUpdateRequest {
    private String workingHours;
    private String phoneNumber;
    private String addInfo;
}
