package com.example.seller_service.dto.request.seller.put;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerProfileUpdateRequest {
    private String fullCompanyName;
    private String shortCompanyName;
    private String description;
}
