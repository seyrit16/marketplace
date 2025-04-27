package com.example.product_service.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductAttrValCreateRequest {
    private String group;
    private String name;
    private String value;
}
