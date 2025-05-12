package com.example.product_service.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageCreateRequest {
    @NotNull(message = "Идентификатор продукта не должен быть пустым")
    private UUID productId;

    @NotNull(message = "Порядок сортировки не должен быть пустым")
    @Min(value = 0, message = "Порядок сортировки не должен быть отрицательным")
    private Integer sortOrder;
}
