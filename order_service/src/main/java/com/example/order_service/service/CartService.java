package com.example.order_service.service;

import com.example.order_service.dto.request.CartRequest;
import com.example.order_service.dto.response.CartResponse;

import java.util.UUID;

public interface CartService {

    void saveCart(UUID userId, CartRequest cart);

    CartResponse getCart(UUID id);

    void deleteCart(UUID id);
}
