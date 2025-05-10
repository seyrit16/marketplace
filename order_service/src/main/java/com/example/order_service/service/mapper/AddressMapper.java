package com.example.order_service.service.mapper;

import com.example.order_service.dto.request.address.AddressCreateRequest;
import com.example.order_service.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address fromAddressCreateRequest(AddressCreateRequest src);
}
