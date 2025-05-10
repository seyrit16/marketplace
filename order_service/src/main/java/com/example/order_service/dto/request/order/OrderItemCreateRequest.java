package com.example.order_service.dto.request.order;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemCreateRequest {
    private UUID productId;
    private Integer quantity;
    private BigDecimal itemPrice;
}
