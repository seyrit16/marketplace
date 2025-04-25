package com.example.product_service.model.document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(indexName = "products")
public class ProductDocument {
    @Id
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime createdAt;

    @Field(type = FieldType.Nested)
    private List<Characteristic> characteristics;

    @Field(type = FieldType.Object)
    private Seller seller;

    @Getter
    @Setter
    public static class Characteristic {
        private String key;
        private String value;
    }

    @Getter
    @Setter
    public static class Seller {
        private String fullCompanyName;
        private String shortCompanyName;
    }
}
