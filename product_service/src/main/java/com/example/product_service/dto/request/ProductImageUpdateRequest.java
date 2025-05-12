package com.example.product_service.dto.request;

import com.example.product_service.model.invariants.ProductImageStatus;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductImageUpdateRequest {
    private ProductImageStatus status = ProductImageStatus.UPDATE;

    @Positive(message = "Идентификатор изображения должен быть положительным числом")
    private Long id;

    @Min(value = 0, message = "Порядок сортировки не должен быть отрицательным")
    private Integer sortOrder;
}
