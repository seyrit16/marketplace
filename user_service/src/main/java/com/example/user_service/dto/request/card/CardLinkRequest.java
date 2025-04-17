package com.example.user_service.dto.request.card;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardLinkRequest {
    String paymentToken;
    String lastFourDigits;
    String cardType;
    String expiryDate;
    String cardHolderName;
    Boolean isDefault = false;
}
