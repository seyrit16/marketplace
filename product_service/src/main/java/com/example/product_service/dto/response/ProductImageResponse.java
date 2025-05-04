package com.example.product_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageResponse {
    private String fileName;
    private String url;
    private Integer sortOrder;
}
