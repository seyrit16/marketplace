package com.example.order_service.client;

import com.example.order_service.dto.rest.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "${user.service.url}",configuration = FeignAuthInterceptor.class)
public interface UserClient {
    @GetMapping("/api/user")
    ResponseEntity<UserResponse> getUserData();
}
