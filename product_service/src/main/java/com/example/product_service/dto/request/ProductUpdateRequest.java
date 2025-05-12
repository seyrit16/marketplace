package com.example.product_service.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductUpdateRequest {
    @NotNull(message = "Идентификатор продукта не должен быть пустым")
    private UUID id;

    @Positive(message = "Идентификатор категории должен быть положительным числом")
    private Long categoryId;

    private String name;
    private String description;

    @Positive(message = "Цена должна быть положительным числом")
    private BigDecimal price;
}
