package com.example.product_service.dto.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductUpdateRequest {
    private UUID id;

    private Long categoryId;

    private String name;
    private String description;
    private BigDecimal price;
}
