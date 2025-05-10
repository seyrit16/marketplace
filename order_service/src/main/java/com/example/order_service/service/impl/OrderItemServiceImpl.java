package com.example.order_service.service.impl;

import com.example.order_service.dto.request.order.OrderItemChangeStatusRequest;
import com.example.order_service.exception.OrderItemNotFoundException;
import com.example.order_service.model.OrderItem;
import com.example.order_service.model.invariant.OrderItemStatus;
import com.example.order_service.repository.OrderItemRepository;
import com.example.order_service.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItem getById(UUID id) {
        return orderItemRepository.findById(id)
                .orElseThrow(()-> new OrderItemNotFoundException("Часть элемента не найдена."));
    }


    @Override
    @Transactional
    public void processing(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.PROCESSING);
        save(item);
    }

    @Override
    @Transactional
    public void inTransit(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.IN_TRANSIT);
        save(item);
    }

    @Override
    @Transactional
    public void atPickupPoint(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.AT_PICKUP_POINT);
        save(item);
    }

    @Override
    @Transactional
    public void delivered(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.DELIVERED);
        save(item);
    }

    @Override
    @Transactional
    public void cancelled(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.CANCELLED);
        item.setAddInfoForStatus(data.getAddInfo());
        save(item);
    }

    @Override
    @Transactional
    public void returned(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.RETURNED);
        item.setAddInfoForStatus(data.getAddInfo());
        save(item);
    }

    @Override
    @Transactional
    public void refunded(OrderItemChangeStatusRequest data) {
        OrderItem item = getById(data.getOrderItemId());
        item.setItemStatus(OrderItemStatus.REFUNDED);
        item.setAddInfoForStatus(data.getAddInfo());
        save(item);
    }
}
