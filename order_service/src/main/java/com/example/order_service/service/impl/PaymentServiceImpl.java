package com.example.order_service.service.impl;

import com.example.order_service.dto.request.PaymentRequest;
import com.example.order_service.exception.PaymentException;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import com.example.order_service.model.invariant.OrderItemStatus;
import com.example.order_service.service.OrderItemService;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.PaymentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    public PaymentServiceImpl(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @Override
    @Transactional
    public void pay(PaymentRequest data) {

        boolean simulateFailure = new Random().nextInt(10) < 2; // 20% шанс ошибки

        if (simulateFailure) {
            throw new PaymentException("Во время оплаты произошла ошибка.");
        }

        Order order = orderService.getById(data.getOrderId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = (UUID) auth.getCredentials();
        if(!userId.equals(order.getUserId())){
            throw new PaymentException("Вы пытаетесь оплатить чужой товар.");
        }

        for(OrderItem item: order.getItems()){
            item.setItemStatus(OrderItemStatus.PAID);
        }
        orderItemService.saveAll(order.getItems());
    }
}
