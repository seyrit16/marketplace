package com.example.order_service.service;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.model.Order;

import java.util.UUID;

public interface OrderService {
    Order save(Order order);
    Order create(OrderCreateRequest data);
    Order getById(UUID id);
}
