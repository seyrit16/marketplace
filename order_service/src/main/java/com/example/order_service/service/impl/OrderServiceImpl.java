package com.example.order_service.service.impl;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import com.example.order_service.model.invariant.OrderItemStatus;
import com.example.order_service.repository.OrderRepository;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.PickupPointService;
import com.example.order_service.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PickupPointService pickupPointService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, PickupPointService pickupPointService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.pickupPointService = pickupPointService;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order create(OrderCreateRequest data) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userid = (UUID) auth.getCredentials();

        Order order = orderMapper.fromOrderCreateRequest(data);
        order.setUserId(userid);
        // расчет полной цены
        BigDecimal fullPrice = BigDecimal.ZERO;
        for(OrderItem orderItem: order.getItems()){
            if(orderItem.getItemPrice() != null) {
                fullPrice = fullPrice.add(orderItem.getItemPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            }
            orderItem.setItemStatus(OrderItemStatus.NEW);
            orderItem.setOrder(order);
        }
        order.setFullPrice(fullPrice);
        order.setPickupPoint(pickupPointService.getById(data.getPickupPointId()));

        return save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getById(UUID id) {

        return orderRepository.findById(id)
                .orElseThrow(()-> new OrderNotFoundException("Заказ не найден."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getForUserAndStatuses(Collection<OrderItemStatus> statuses) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userid = (UUID) auth.getCredentials();

        return orderRepository.findOrdersByUserIdAndItemStatuses(userid,statuses).get();
    }
}
