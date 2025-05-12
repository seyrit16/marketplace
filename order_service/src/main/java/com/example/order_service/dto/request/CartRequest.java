package com.example.order_service.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartRequest {
    @NotEmpty(message = "Список идентификаторов продуктов не должен быть пустым")
    @NotNull(message = "Список идентификаторов продуктов не должен быть пустым")
    private List<UUID> productIds;
}
