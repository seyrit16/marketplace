package com.example.order_service.dto.response.order;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResponse {
    private UUID id;
    private UUID userId;
    private BigDecimal fullPrice;
    private UUID pickupPointId;
    private List<OrderItemResponse> items = new ArrayList<>();
    private LocalDateTime createdAt;
}
