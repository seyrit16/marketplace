package com.example.order_service.service.mapper;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order fromOrderCreateRequest(OrderCreateRequest src);
}
