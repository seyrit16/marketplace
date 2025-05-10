package com.example.order_service.service;

import com.example.order_service.dto.request.pickup_point.PickupPointCreateRequest;
import com.example.order_service.dto.request.pickup_point.PickupPointUpdateRequest;
import com.example.order_service.model.PickupPoint;

import java.util.UUID;

public interface PickupPointService {
    PickupPoint save(PickupPoint pickupPoint);
    PickupPoint create(PickupPointCreateRequest data);
    PickupPoint getById(UUID id);
    PickupPoint getFromAuth();
    PickupPoint update(PickupPointUpdateRequest data);
}
