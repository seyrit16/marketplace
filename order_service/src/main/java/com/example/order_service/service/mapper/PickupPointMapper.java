package com.example.order_service.service.mapper;

import com.example.order_service.dto.request.pickup_point.PickupPointCreateRequest;
import com.example.order_service.dto.request.pickup_point.PickupPointUpdateRequest;
import com.example.order_service.dto.response.pickup_point.PickupPointForUserResponse;
import com.example.order_service.dto.response.pickup_point.PickupPointFullDataResponse;
import com.example.order_service.model.PickupPoint;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PickupPointMapper {
    PickupPoint fromPickupPointCreateRequest(PickupPointCreateRequest src);

    PickupPointFullDataResponse toPickupPointFullDataResponse(PickupPoint src);

    PickupPointForUserResponse toPickupPointForUserResponse(PickupPoint src);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PickupPoint updateFromPickupPointUpdateRequest(
            PickupPointUpdateRequest src,
            @MappingTarget PickupPoint dest
    );
}
