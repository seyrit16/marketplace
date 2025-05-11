package com.example.order_service.service;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.model.Order;
import com.example.order_service.model.invariant.OrderItemStatus;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order save(Order order);
    Order create(OrderCreateRequest data);
    Order getById(UUID id);
    List<Order> getForUserAndStatuses(Collection<OrderItemStatus> statuses);
}
