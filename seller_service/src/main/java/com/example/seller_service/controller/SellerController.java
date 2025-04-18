package com.example.seller_service.controller;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping()
    public ResponseEntity<Void> createSeller(@RequestBody SellerCreateRequest sellerCreateRequest){

        sellerService.createSeller(sellerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
