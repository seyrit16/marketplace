package com.example.order_service.service.mapper;

import com.example.order_service.dto.request.order.OrderItemCreateRequest;
import com.example.order_service.dto.response.order.OrderItemResponse;
import com.example.order_service.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    OrderItem fromOrderItemCreateRequest(OrderItemCreateRequest src);

    @Mapping(source = "order.id", target = "orderId")
    OrderItemResponse toOrderItemResponse(OrderItem src);
}
