package com.example.order_service.controller;

import com.example.order_service.dto.request.CartRequest;
import com.example.order_service.dto.response.CartResponse;
import com.example.order_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@Validated
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCart(@Valid @RequestBody CartRequest cartRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = (UUID) auth.getCredentials();

        cartService.saveCart(userId, cartRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<CartResponse> getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = (UUID) auth.getCredentials();

        CartResponse cartResponse = cartService.getCart(userId);

        return ResponseEntity.ok().body(cartResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = (UUID) auth.getCredentials();

        cartService.deleteCart(userId);

        return ResponseEntity.ok().build();
    }
}
