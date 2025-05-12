package com.example.order_service.controller;

import com.example.order_service.dto.request.order.OrderItemChangeStatusRequest;
import com.example.order_service.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/order_item")
@Validated
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/processing")
    public ResponseEntity<Void> processing(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.processing(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/in_transit")
    public ResponseEntity<Void> inTransit(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.inTransit(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/at_pickup_point")
    public ResponseEntity<Void> atPickupPoint(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.atPickupPoint(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/delivered")
    public ResponseEntity<Void> delivered(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.delivered(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/cancelled")
    public ResponseEntity<Void> cancelled(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.cancelled(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/returned")
    public ResponseEntity<Void> returned(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.returned(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/refunded")
    public ResponseEntity<Void> refunded(@Valid @RequestBody OrderItemChangeStatusRequest data) {
        orderItemService.refunded(data);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
