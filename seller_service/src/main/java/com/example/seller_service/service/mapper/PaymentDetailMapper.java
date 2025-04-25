package com.example.seller_service.service.mapper;

import com.example.seller_service.dto.request.seller.PaymentDetailCreateRequest;
import com.example.seller_service.dto.request.seller.put.PaymentDetailUpdateRequest;
import com.example.seller_service.dto.response.seller.PaymentDetailResponse;
import com.example.seller_service.model.PaymentDetail;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentDetailMapper {

    PaymentDetail fromPaymentDetailCreateRequest(PaymentDetailCreateRequest paymentDetailCreateRequest);

    PaymentDetailResponse toPaymentDetailResponse(PaymentDetail paymentDetail);

    @AfterMapping
    default void linkBack(@MappingTarget PaymentDetail detail, PaymentDetailCreateRequest request) {
    }

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updatePaymentDetailFromDTO(PaymentDetailUpdateRequest paymentDetailUpdateDTO, @MappingTarget PaymentDetail paymentDetail);
}
