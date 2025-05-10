package com.example.order_service.service;

import com.example.order_service.dto.request.order.OrderItemChangeStatusRequest;
import com.example.order_service.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    OrderItem save(OrderItem orderItem);
    List<OrderItem> saveAll(List<OrderItem> orderItems);
    OrderItem getById(UUID id);

    void processing(OrderItemChangeStatusRequest data);
    void inTransit(OrderItemChangeStatusRequest data);
    void atPickupPoint(OrderItemChangeStatusRequest data);
    void delivered(OrderItemChangeStatusRequest data);
    void cancelled(OrderItemChangeStatusRequest data);
    void returned(OrderItemChangeStatusRequest data);
    void refunded(OrderItemChangeStatusRequest data);
}
