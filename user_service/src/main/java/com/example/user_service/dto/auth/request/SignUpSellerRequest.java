package com.example.user_service.dto.auth.request;

import com.example.user_service.dto.rest.request.seller.SellerCreateRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpSellerRequest {
    private String email;
    private String password;

    private SellerCreateRequest sellerCreateRequest;

    private String code;
}
