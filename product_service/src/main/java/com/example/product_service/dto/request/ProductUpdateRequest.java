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

    private String name;
    private String description;
    private BigDecimal price;
//     [
//        {group1}:[
//            {key1}: {value1}
//            {key2}: {value2}
//        ],
//        <group2>:[
//            {key1}: {value1}
//            {key2}: {value2}
//        ]
//     ]
    private String attributes;
}
