package com.example.product_service.model.document;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "products")
public class ProductDocument {
    @Id
    private UUID id;

    private String categoryName;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer rating;

    private LocalDateTime createdAt;

    @Field(type = FieldType.Nested)
    private List<Attribute> attributes = new ArrayList<>();

    @Field(type = FieldType.Object)
    private Seller seller;

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Attribute {
        private String group;
        private String name;
        private String value;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    public static class Seller {
        private String fullCompanyName;
        private String shortCompanyName;
    }
}
