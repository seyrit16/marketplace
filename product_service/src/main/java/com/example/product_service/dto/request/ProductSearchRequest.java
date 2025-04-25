package com.example.product_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchRequest {
    private String query;
    private int page;
    private int size;
    private String sortBy;
    private String sortOrder;
}
