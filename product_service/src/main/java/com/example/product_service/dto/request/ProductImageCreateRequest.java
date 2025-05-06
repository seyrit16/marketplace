package com.example.product_service.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageCreateRequest {
    private UUID productId;

    private Integer sortOrder;
}
