package com.example.seller_service.service.impl;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.dto.request.seller.put.PaymentDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.PersonDetailUpdateRequest;
import com.example.seller_service.dto.request.seller.put.SellerProfileUpdateRequest;
import com.example.seller_service.exception.UserNotFoundException;
import com.example.seller_service.service.mapper.PersonMapper;
import com.example.seller_service.service.mapper.SellerMapper;
import com.example.seller_service.service.mapper.PaymentDetailMapper;
import com.example.seller_service.model.SellerProfile;
import com.example.seller_service.repository.SellerRepository;
import com.example.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final PaymentDetailMapper paymentDetailMapper;
    private final PersonMapper personMapper;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, SellerMapper sellerMapper, PaymentDetailMapper paymentDetailMapper, PersonMapper personMapper) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.paymentDetailMapper = paymentDetailMapper;
        this.personMapper = personMapper;
    }


    @Override
    @Transactional
    public SellerProfile save(SellerProfile sellerProfile) {
        return sellerRepository.save(sellerProfile);
    }

    @Override
    @Transactional
    public SellerProfile createSeller(SellerCreateRequest sellerCreateRequest) {

        SellerProfile sellerProfile = sellerMapper.fromSellerCreateRequest(sellerCreateRequest);
        sellerProfile.setUserId(sellerCreateRequest.getId());
        sellerProfile.setIsVerified(false);
        sellerProfile.getPaymentDetail().setIsVerified(false);

        return save(sellerProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile getSellerProfileById(UUID id){
        return sellerRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Такой пользователь не найден.")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public SellerProfile getSellerProfileFromAuth() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID sellerId = (UUID) auth.getCredentials();
        return getSellerProfileById(sellerId);
    }

    @Override
    @Transactional
    public SellerProfile updateSellerProfile(SellerProfileUpdateRequest sellerProfileUpdateRequest) {

        SellerProfile sellerProfile = getSellerProfileFromAuth();
        sellerMapper.updateSellerProfileFromDTO(sellerProfileUpdateRequest, sellerProfile);
        return save(sellerProfile);
    }

    @Override
    @Transactional
    public SellerProfile updateSellerPersonDetail(PersonDetailUpdateRequest personDetailUpdateRequest) {

        SellerProfile sellerProfile = getSellerProfileFromAuth();
        personMapper.updatePersonDetailFromDTO(personDetailUpdateRequest,sellerProfile.getPersonDetail());
        return save(sellerProfile);
    }

    @Override
    @Transactional
    public SellerProfile updateSellerPaymentDetail(PaymentDetailUpdateRequest paymentDetailUpdateRequest) {

        SellerProfile sellerProfile = getSellerProfileFromAuth();
        paymentDetailMapper.updatePaymentDetailFromDTO(paymentDetailUpdateRequest,sellerProfile.getPaymentDetail());
        return save(sellerProfile);
    }
}
