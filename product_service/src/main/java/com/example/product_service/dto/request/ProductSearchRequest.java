package com.example.product_service.dto.request;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSearchRequest {
    private String query;
    private String sortBy;
    private String sortOrder;
    private Pageable pageable;
}
