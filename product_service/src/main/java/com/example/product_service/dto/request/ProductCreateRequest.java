package com.example.product_service.dto.request;

import lombok.*;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCreateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private List<ProductAttrValCreateRequest> attributes;
    private List<File> files;
    //private String urlImagesDirectory;
}
