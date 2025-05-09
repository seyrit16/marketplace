package com.example.order_service.controller;

import com.example.order_service.dto.request.pickup_point.PickupPointUpdateRequest;
import com.example.order_service.dto.response.pickup_point.PickupPointForUserResponse;
import com.example.order_service.dto.response.pickup_point.PickupPointFullDataResponse;
import com.example.order_service.model.PickupPoint;
import com.example.order_service.service.PickupPointService;
import com.example.order_service.service.mapper.PickupPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pickup_point")
public class PickupPointController {
    private final PickupPointService pickupPointService;
    private final PickupPointMapper pickupPointMapper;

    @Autowired
    public PickupPointController(PickupPointService pickupPointService, PickupPointMapper pickupPointMapper) {
        this.pickupPointService = pickupPointService;
        this.pickupPointMapper = pickupPointMapper;
    }

    @GetMapping("/full")
    public ResponseEntity<PickupPointFullDataResponse> getPickupPointFullData(){

        PickupPoint pickupPoint = pickupPointService.getFromAuth();
        PickupPointFullDataResponse response = pickupPointMapper.toPickupPointFullDataResponse(pickupPoint);

        // TODO 09.05 сделать получение данных пользователя с сервиса пользователя и добавление их в response

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/for_user")
    public ResponseEntity<PickupPointForUserResponse> getPickupPointForUser(){

        PickupPoint pickupPoint = pickupPointService.getFromAuth();
        PickupPointForUserResponse response = pickupPointMapper.toPickupPointForUserResponse(pickupPoint);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<PickupPointFullDataResponse> updatePickupPoint(@RequestBody PickupPointUpdateRequest data){

        PickupPoint pickupPoint = pickupPointService.update(data);
        PickupPointFullDataResponse response = pickupPointMapper.toPickupPointFullDataResponse(pickupPoint);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
