package com.example.product_service.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryCreateRequest {
    @NotBlank(message = "Название категории не должно быть пустым")
    private String name;
    @Positive(message = "Идентификатор корневой категории должен быть положительным числом")
    private Long rootCategoryId;
}
