package com.example.user_service.dto.rest.request.pickup_point;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PPAddressCreateRequest {
    private String region;
    private String city;
    private String street;
    private String houseNumber;

    private String postalCode;
}
