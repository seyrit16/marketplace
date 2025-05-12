package com.example.order_service.controller;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.dto.response.order.OrderResponse;
import com.example.order_service.model.Order;
import com.example.order_service.model.invariant.OrderItemStatus;
import com.example.order_service.service.OrderService;
import com.example.order_service.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@NotNull(message = "Идентификатор заказа не должен быть пустым") @PathVariable UUID id) {
        Order order = orderService.getById(id);
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderResponse);
    }

    @GetMapping("/for_user")
    public ResponseEntity<List<OrderResponse>> getOrdersByUserIdAndStatuses(@RequestParam(name = "statuses", required = false) List<OrderItemStatus> statuses) {
        List<Order> orders = orderService.getForUserAndStatuses(
                (statuses == null || statuses.isEmpty())
                        ? Arrays.stream(OrderItemStatus.values()).toList()
                        : statuses
        );
        List<OrderResponse> response = orders.stream().map(orderMapper::toOrderResponse).toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderCreateRequest data) {
        orderService.create(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}