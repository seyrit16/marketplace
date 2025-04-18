package com.example.seller_service.mapper;

import com.example.seller_service.dto.request.seller.PersonCreateRequest;
import com.example.seller_service.model.PersonDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonDetail fromPersonCreateRequest(PersonCreateRequest personCreateRequest);
}
