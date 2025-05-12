package com.example.order_service.controller;

import com.example.order_service.dto.request.pickup_point.PickupPointCreateRequest;
import com.example.order_service.service.PickupPointService;
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
@RequestMapping("/auth")
@Validated
public class AuthenticationController {

    private final PickupPointService pickupPointService;

    @Autowired
    public AuthenticationController(PickupPointService pickupPointService) {
        this.pickupPointService = pickupPointService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPickupPoint(@Valid @RequestBody PickupPointCreateRequest data) {
        pickupPointService.create(data);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
