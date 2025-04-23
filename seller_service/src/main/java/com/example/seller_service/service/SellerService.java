package com.example.seller_service.service;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.dto.request.seller.put.PaymentDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.PersonDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.SellerProfileUpdateRequest;
import com.example.seller_service.model.SellerProfile;

import java.util.UUID;

public interface SellerService {

    SellerProfile save(SellerProfile sellerProfile);
    SellerProfile createSeller(SellerCreateRequest sellerCreateRequest);

    SellerProfile getSellerProfileById(UUID id);

    SellerProfile getSellerProfileFromAuth();

    SellerProfile updateSellerProfile(SellerProfileUpdateRequest sellerProfileUpdateRequest);

    SellerProfile updateSellerPersonDetail(PersonDetailUpdateRequest personDetailUpdateRequest);

    SellerProfile updateSellerPaymentDetail(PaymentDetailUpdateRequest paymentDetailUpdateRequest);
}
