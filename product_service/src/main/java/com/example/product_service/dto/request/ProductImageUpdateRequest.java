package com.example.product_service.dto.request;

import com.example.product_service.model.invariants.ProductImageStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageUpdateRequest {
    private ProductImageStatus status;

    private Long id;
    private Integer sortOrder;
}
