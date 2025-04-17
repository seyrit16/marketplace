package com.example.user_service.dto.response.card;

import com.example.user_service.model.User;
import lombok.*;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CardResponse {
    private Long id;
    private String lastFourDigits;
    private String cardType;
    private String expiryDate;
    private String cardHolderName;
    private Boolean isDefault;
}
