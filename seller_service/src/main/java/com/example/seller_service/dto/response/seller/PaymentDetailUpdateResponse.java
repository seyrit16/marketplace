package com.example.seller_service.dto.response.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetailUpdateResponse {
    private String bankAccountNumber;
    private String bankName;
    private String bic;
    private String accountHolderName;
    private String inn;
}
