package com.example.product_service.dto.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    @NotBlank(message = "Название продукта не должно быть пустым")
    private String name;

    private String description;

    @NotNull(message = "Цена не должна быть пустой")
    @Positive(message = "Цена должна быть положительным числом")
    private BigDecimal price;

    @NotNull(message = "Идентификатор категории не должен быть пустым")
    @Positive(message = "Идентификатор категории должен быть положительным числом")
    private Long categoryId;

    @Valid
    private List<ProductAttrValCreateRequest> attributes;

    @Valid
    private List<ProductImageCreateRequest> images;
}
