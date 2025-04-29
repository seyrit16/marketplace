package com.example.product_service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryCreateRequest {
    private String name;
    private Long rootCategoryId;
}
