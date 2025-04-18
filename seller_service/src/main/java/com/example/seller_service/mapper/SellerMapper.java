package com.example.seller_service.mapper;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.model.SellerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {PersonMapper.class, SellerPaymentDetailMapper.class })
public interface SellerMapper {

    //@Mapping(source = "person", target = "personDetail")
    //@Mapping(source = "paymentDetail", target = "sellerPaymentDetail")
    SellerProfile fromSellerCreateRequest(SellerCreateRequest sellerCreateRequest);
}
