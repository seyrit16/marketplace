package com.example.order_service.controller;

import com.example.order_service.client.UserClient;
import com.example.order_service.dto.request.pickup_point.PickupPointUpdateRequest;
import com.example.order_service.dto.response.pickup_point.PickupPointForUserResponse;
import com.example.order_service.dto.response.pickup_point.PickupPointFullDataResponse;
import com.example.order_service.dto.rest.response.UserResponse;
import com.example.order_service.model.PickupPoint;
import com.example.order_service.service.PickupPointService;
import com.example.order_service.service.mapper.PickupPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pickup_point")
@Validated
public class PickupPointController {
    private final PickupPointService pickupPointService;
    private final PickupPointMapper pickupPointMapper;
    private final UserClient userClient;

    @Autowired
    public PickupPointController(PickupPointService pickupPointService, PickupPointMapper pickupPointMapper, UserClient userClient) {
        this.pickupPointService = pickupPointService;
        this.pickupPointMapper = pickupPointMapper;
        this.userClient = userClient;
    }

    @GetMapping("/full")
    public ResponseEntity<PickupPointFullDataResponse> getPickupPointFullData() {
        PickupPoint pickupPoint = pickupPointService.getFromAuth();
        PickupPointFullDataResponse response = pickupPointMapper.toPickupPointFullDataResponse(pickupPoint);

        UserResponse userResponse = userClient.getUserData().getBody();
        response.setUserInfo(userResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/for_user")
    public ResponseEntity<PickupPointForUserResponse> getPickupPointForUser() {
        PickupPoint pickupPoint = pickupPointService.getFromAuth();
        PickupPointForUserResponse response = pickupPointMapper.toPickupPointForUserResponse(pickupPoint);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<PickupPointFullDataResponse> updatePickupPoint(@Valid @RequestBody PickupPointUpdateRequest data) {
        PickupPoint pickupPoint = pickupPointService.update(data);
        PickupPointFullDataResponse response = pickupPointMapper.toPickupPointFullDataResponse(pickupPoint);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
