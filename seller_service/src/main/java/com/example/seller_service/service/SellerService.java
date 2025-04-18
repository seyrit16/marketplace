package com.example.seller_service.service;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.model.SellerProfile;

public interface SellerService {

    SellerProfile save(SellerProfile sellerProfile);
    SellerProfile createSeller(SellerCreateRequest sellerCreateRequest);
}
