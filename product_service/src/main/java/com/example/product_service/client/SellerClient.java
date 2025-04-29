package com.example.product_service.client;


import com.example.product_service.dto.rest.SellerForCreateProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "seller-service", url = "${seller.service.url}", configuration = FeignAuthInterceptor.class)
public interface SellerClient {

    @GetMapping("/api/seller")
    ResponseEntity<SellerForCreateProductResponse> getSellerData();
}
