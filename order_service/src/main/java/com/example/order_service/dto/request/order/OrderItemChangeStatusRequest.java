package com.example.order_service.dto.request.order;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemChangeStatusRequest {
    private UUID orderItemId;
    private String addInfo;
}
