package com.example.order_service.dto.request.address;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressCreateRequest {
    private String region;
    private String city;
    private String street;
    private String houseNumber;

    private String postalCode;
}
