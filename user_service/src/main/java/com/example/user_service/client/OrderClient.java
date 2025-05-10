package com.example.user_service.client;

import com.example.user_service.dto.rest.request.pickup_point.PickupPointCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {
    @PostMapping("/auth/create")
    void createPickupPoint(PickupPointCreateRequest pickupPointCreateRequest);
}
