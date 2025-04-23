package com.example.seller_service.dto.response.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerProfileUpdateResponse {
    private String fullCompanyName;
    private String shortCompanyName;
    private String description;
}
