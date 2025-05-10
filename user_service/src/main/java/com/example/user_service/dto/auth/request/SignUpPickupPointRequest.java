package com.example.user_service.dto.auth.request;

import com.example.user_service.dto.rest.request.pickup_point.PickupPointCreateRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpPickupPointRequest {
    private String email;
    private String password;

    private PickupPointCreateRequest pickupPointCreateRequest;

    private String code;
}

