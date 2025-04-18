package com.example.seller_service.service.impl;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.mapper.PersonMapper;
import com.example.seller_service.mapper.SellerMapper;
import com.example.seller_service.mapper.SellerPaymentDetailMapper;
import com.example.seller_service.model.SellerProfile;
import com.example.seller_service.repository.SellerRepository;
import com.example.seller_service.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final SellerPaymentDetailMapper sellerPaymentDetailMapper;
    private final PersonMapper personMapper;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, SellerMapper sellerMapper, SellerPaymentDetailMapper sellerPaymentDetailMapper, PersonMapper personMapper) {
        this.sellerRepository = sellerRepository;
        this.sellerMapper = sellerMapper;
        this.sellerPaymentDetailMapper = sellerPaymentDetailMapper;
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
        sellerProfile.setSellerPaymentDetail(
                sellerPaymentDetailMapper.fromPaymentDetailCreateRequest(
                        sellerCreateRequest.getPaymentDetail()
                )
        );
        sellerProfile.getSellerPaymentDetail().setSeller(sellerProfile);
        sellerProfile.setPersonDetail(
                personMapper.fromPersonCreateRequest(
                        sellerCreateRequest.getPerson()
                )
        );
        sellerProfile.getPersonDetail().setSeller(sellerProfile);

        return save(sellerProfile);
    }
}
