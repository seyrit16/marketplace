package com.example.order_service.dto.request.order;

import com.example.order_service.model.PickupPoint;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateRequest {
    private UUID pickupPointId;
    private List<OrderItemCreateRequest> items;
}
