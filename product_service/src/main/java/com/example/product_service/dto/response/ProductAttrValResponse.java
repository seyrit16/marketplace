package com.example.product_service.dto.response;

import com.example.product_service.model.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductAttrValResponse {
    private String group;
    private String name;
    private String value;
}
