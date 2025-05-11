package com.example.order_service.service.mapper;

import com.example.order_service.dto.request.order.OrderCreateRequest;
import com.example.order_service.dto.response.order.OrderResponse;
import com.example.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderItemMapper.class})
public interface OrderMapper {
    Order fromOrderCreateRequest(OrderCreateRequest src);
    @Mapping(source = "pickupPoint.id", target = "pickupPointId")
    OrderResponse toOrderResponse(Order src);
}
