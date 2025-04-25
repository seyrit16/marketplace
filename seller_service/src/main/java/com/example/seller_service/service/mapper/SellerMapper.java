package com.example.seller_service.service.mapper;

import com.example.seller_service.dto.request.seller.SellerCreateRequest;
import com.example.seller_service.dto.request.seller.put.SellerProfileUpdateRequest;
import com.example.seller_service.dto.response.seller.SellerProfileResponse;
import com.example.seller_service.model.SellerProfile;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {
        PersonMapper.class,
        PaymentDetailMapper.class
})
public interface SellerMapper {

    @Mapping(source = "person", target = "personDetail")
    @Mapping(source = "paymentDetail", target = "paymentDetail")
    SellerProfile fromSellerCreateRequest(SellerCreateRequest sellerCreateRequest);

    SellerProfileResponse toSellerProfileResponse(SellerProfile sellerProfile);

    @AfterMapping
    default void setReverseRelations(@MappingTarget SellerProfile sellerProfile) {
        if (sellerProfile.getPaymentDetail() != null) {
            sellerProfile.getPaymentDetail().setSeller(sellerProfile);
        }
        if (sellerProfile.getPersonDetail() != null) {
            sellerProfile.getPersonDetail().setSeller(sellerProfile);
        }
    }


    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updateSellerProfileFromDTO(SellerProfileUpdateRequest sellerProfileUpdateRequest, @MappingTarget SellerProfile sellerProfile);
}
