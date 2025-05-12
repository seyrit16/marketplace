package com.example.product_service.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductAttrValCreateRequest {
    @NotBlank(message = "Группа атрибута не должна быть пустой")
    private String group;

    @NotBlank(message = "Имя атрибута не должно быть пустым")
    private String name;

    @NotBlank(message = "Значение атрибута не должно быть пустым")
    private String value;
}
