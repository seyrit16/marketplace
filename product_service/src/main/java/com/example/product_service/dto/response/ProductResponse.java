package com.example.product_service.dto.response;

import com.example.product_service.model.Category;
import com.example.product_service.model.ProductAttributeValue;
import com.example.product_service.model.ProductImage;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponse {
    private UUID id;
    private UUID sellerId;
    private CategoryResponse category;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer rating;
    private Integer numberOfPurchases;
    private List<ProductAttrValResponse> attributes = new ArrayList<>();
    private List<ProductImageResponse> images = new ArrayList<>();

    private Boolean deleted;
}
