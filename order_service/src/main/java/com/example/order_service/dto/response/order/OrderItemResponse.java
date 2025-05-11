package com.example.order_service.dto.response.order;

import com.example.order_service.model.invariant.OrderItemStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemResponse {
    private UUID id;
    private UUID orderId;
    private UUID productId;
    private Integer quantity;
    private BigDecimal itemPrice;
    private OrderItemStatus itemStatus;
    private String addInfoForStatus;
}
