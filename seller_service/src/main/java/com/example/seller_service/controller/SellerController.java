package com.example.seller_service.controller;

import com.example.seller_service.client.UserClient;
import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.dto.request.seller.put.PaymentDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.PersonDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.SellerProfileUpdateRequest;
import com.example.seller_service.dto.response.seller.PaymentDetailResponse;
import com.example.seller_service.dto.response.seller.PersonDetailResponse;
import com.example.seller_service.dto.response.seller.SellerDataResponse;
import com.example.seller_service.dto.response.seller.SellerProfileResponse;
import com.example.seller_service.dto.rest.response.UserResponse;
import com.example.seller_service.service.mapper.PaymentDetailMapper;
import com.example.seller_service.service.mapper.PersonMapper;
import com.example.seller_service.service.mapper.SellerMapper;
import com.example.seller_service.model.SellerProfile;
import com.example.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/seller")
@Validated
public class SellerController {

    private final SellerService sellerService;
    private final SellerMapper sellerMapper;
    private final PersonMapper personMapper;
    private final PaymentDetailMapper paymentDetailMapper;
    private final UserClient userClient;

    @Autowired
    public SellerController(
            SellerService sellerService, SellerMapper sellerMapper,
            PersonMapper personMapper, PaymentDetailMapper paymentDetailMapper,
            UserClient userClient
    ) {
        this.sellerService = sellerService;
        this.sellerMapper = sellerMapper;
        this.personMapper = personMapper;
        this.paymentDetailMapper = paymentDetailMapper;
        this.userClient = userClient;
    }

    @GetMapping()
    public ResponseEntity<SellerDataResponse> getSellerData() {
        SellerProfile sellerProfile = sellerService.getSellerProfileFromAuth();
        UserResponse userResponse = userClient.getUserData().getBody();

        SellerDataResponse sellerDataResponse = new SellerDataResponse();
        sellerDataResponse.setEmail(userResponse.getEmail());
        sellerDataResponse.setIsActive(userResponse.getIsActive());
        sellerDataResponse.setIsLocked(userResponse.getIsLocked());
        sellerDataResponse.setSellerProfile(sellerMapper.toSellerProfileResponse(sellerProfile));
        sellerDataResponse.setPersonDetail(personMapper.toPersonDetailResponse(sellerProfile.getPersonDetail()));
        sellerDataResponse.setPaymentDetail(paymentDetailMapper.toPaymentDetailResponse(sellerProfile.getPaymentDetail()));

        return ResponseEntity.status(HttpStatus.OK)
                .body(sellerDataResponse);
    }

    @PostMapping("/auth/create")
    public ResponseEntity<Void> createSeller(@Valid @RequestBody SellerCreateRequest sellerCreateRequest) {
        sellerService.createSeller(sellerCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/seller_profile")
    public ResponseEntity<SellerProfileResponse> updateSellerProfile(@Valid @RequestBody SellerProfileUpdateRequest sellerProfileUpdateRequest) {
        SellerProfile sellerProfile = sellerService.updateSellerProfile(sellerProfileUpdateRequest);
        SellerProfileResponse sellerProfileResponse = sellerMapper.toSellerProfileResponse(sellerProfile);
        return ResponseEntity.status(HttpStatus.OK).body(sellerProfileResponse);
    }

    @PutMapping("/update/person_detail")
    public ResponseEntity<PersonDetailResponse> updateSellerPersonDetail(@Valid @RequestBody PersonDetailUpdateRequest personDetailUpdateRequest) {
        SellerProfile sellerProfile = sellerService.updateSellerPersonDetail(personDetailUpdateRequest);
        PersonDetailResponse personDetailResponse = personMapper.toPersonDetailResponse(sellerProfile.getPersonDetail());
        return ResponseEntity.status(HttpStatus.OK).body(personDetailResponse);
    }

    @PutMapping("/update/payment_detail")
    public ResponseEntity<PaymentDetailResponse> updateSellerPaymentDetail(@Valid @RequestBody PaymentDetailUpdateRequest paymentDetailUpdateRequest) {
        SellerProfile sellerProfile = sellerService.updateSellerPaymentDetail(paymentDetailUpdateRequest);
        PaymentDetailResponse paymentDetailResponse = paymentDetailMapper.toPaymentDetailResponse(sellerProfile.getPaymentDetail());
        return ResponseEntity.status(HttpStatus.OK).body(paymentDetailResponse);
    }
}
