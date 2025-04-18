package com.example.seller_service.mapper;

import com.example.seller_service.dto.request.seller.PaymentDetailCreateRequest;
import com.example.seller_service.model.SellerPaymentDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SellerPaymentDetailMapper {

    SellerPaymentDetail fromPaymentDetailCreateRequest(PaymentDetailCreateRequest paymentDetailCreateRequest);
}
