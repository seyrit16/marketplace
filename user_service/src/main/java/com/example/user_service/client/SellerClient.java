package com.example.user_service.client;

import com.example.user_service.dto.rest.request.seller.SellerCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "seller-service", url = "${seller.service.url}")
public interface SellerClient {
    @PostMapping("/api/seller/auth/create")
    void createSeller(SellerCreateRequest sellerCreateRequest);
}
