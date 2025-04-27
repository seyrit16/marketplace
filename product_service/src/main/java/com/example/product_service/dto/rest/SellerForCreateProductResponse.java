package com.example.product_service.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerForCreateProductResponse {
    private String fullCompanyName;
    private String shortCompanyName;
}
