package com.example.order_service.dto.request.order;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemCreateRequest {
    @NotNull(message = "Идентификатор продукта не должен быть пустым")
    private UUID productId;

    @NotNull(message = "Количество не должно быть пустым")
    @Min(value = 1, message = "Количество должно быть не менее 1")
    private Integer quantity;

    @NotNull(message = "Цена элемента не должна быть пустой")
    @Positive(message = "Цена элемента должна быть положительным числом")
    private BigDecimal itemPrice;
}
