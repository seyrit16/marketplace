package com.example.order_service.dto.response.address;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressResponse {
    private String region;
    private String city;
    private String street;
    private String houseNumber;

    private String postalCode;
}
