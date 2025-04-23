package com.example.seller_service.controller;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.dto.request.seller.put.PaymentDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.PersonDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.SellerProfileUpdateRequest;
import com.example.seller_service.dto.response.seller.PaymentDetailUpdateResponse;
import com.example.seller_service.dto.response.seller.PersonDetailUpdateResponse;
import com.example.seller_service.dto.response.seller.SellerProfileUpdateResponse;
import com.example.seller_service.service.mapper.PaymentDetailMapper;
import com.example.seller_service.service.mapper.PersonMapper;
import com.example.seller_service.service.mapper.SellerMapper;
import com.example.seller_service.model.SellerProfile;
import com.example.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
public class SellerController {

    private final SellerService sellerService;
    private final SellerMapper sellerMapper;
    private final PersonMapper personMapper;
    private final PaymentDetailMapper paymentDetailMapper;

    @Autowired
    public SellerController(SellerService sellerService, SellerMapper sellerMapper, PersonMapper personMapper, PaymentDetailMapper paymentDetailMapper) {
        this.sellerService = sellerService;
        this.sellerMapper = sellerMapper;
        this.personMapper = personMapper;
        this.paymentDetailMapper = paymentDetailMapper;
    }

    @PostMapping("/auth/create")
    public ResponseEntity<Void> createSeller(@RequestBody SellerCreateRequest sellerCreateRequest) {

        sellerService.createSeller(sellerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/seller_profile")
    public ResponseEntity<SellerProfileUpdateResponse> updateSellerProfile(@RequestBody SellerProfileUpdateRequest sellerProfileUpdateRequest) {

        SellerProfile sellerProfile = sellerService.updateSellerProfile(sellerProfileUpdateRequest);
        SellerProfileUpdateResponse sellerProfileUpdateResponse =
                sellerMapper.toSellerProfileUpdateResponse(sellerProfile);
        return ResponseEntity.status(HttpStatus.OK).body(sellerProfileUpdateResponse);
    }

    @PutMapping("/update/person_detail")
    public ResponseEntity<PersonDetailUpdateResponse> updateSellerPersonDetail(@RequestBody PersonDetailUpdateRequest personDetailUpdateRequest) {

        SellerProfile sellerProfile = sellerService.updateSellerPersonDetail(personDetailUpdateRequest);
        PersonDetailUpdateResponse personDetailUpdateResponse =
                personMapper.toPersonDetailUpdateResponse(sellerProfile.getPersonDetail());
        return ResponseEntity.status(HttpStatus.OK).body(personDetailUpdateResponse);
    }

    @PutMapping("/update/payment_detail")
    public ResponseEntity<PaymentDetailUpdateResponse> updateSellerPaymentDetail(@RequestBody PaymentDetailUpdateRequest paymentDetailUpdateRequest) {

        SellerProfile sellerProfile = sellerService.updateSellerPaymentDetail(paymentDetailUpdateRequest);
        PaymentDetailUpdateResponse paymentDetailUpdateResponse =
                paymentDetailMapper.toPaymentDetailUpdateResponse(sellerProfile.getSellerPaymentDetail());
        return ResponseEntity.status(HttpStatus.OK).body(paymentDetailUpdateResponse);
    }
}
