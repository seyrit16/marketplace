package com.example.seller_service.dto.response.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerDataResponse {
    private String email;
    private Boolean isActive;
    private Boolean isLocked;

    private SellerProfileResponse sellerProfile;
    private PersonDetailResponse personDetail;
    private PaymentDetailResponse paymentDetail;
}
