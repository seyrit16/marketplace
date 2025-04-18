package com.example.user_service.dto.rest.request.seller;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerCreateRequest {
    private UUID id;
    private PersonCreateRequest person;
    private String fullCompanyName;
    private String shortCompanyName;
    private String description;
    private PaymentDetailCreateRequest paymentDetail;
}
