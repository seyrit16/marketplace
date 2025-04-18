package com.example.seller_service.dto.request.seller;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetailCreateRequest {
    private String bankAccountNumber;
    private String bankName;
    private String bic;
    private String accountHolderName;
    private String inn;
}
