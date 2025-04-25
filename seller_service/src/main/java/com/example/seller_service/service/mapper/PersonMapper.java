package com.example.seller_service.service.mapper;

import com.example.seller_service.dto.request.seller.PersonCreateRequest;
import com.example.seller_service.dto.request.seller.put.PersonDetailUpdateRequest;
import com.example.seller_service.dto.response.seller.PersonDetailResponse;
import com.example.seller_service.model.PersonDetail;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonDetail fromPersonCreateRequest(PersonCreateRequest personCreateRequest);

    PersonDetailResponse toPersonDetailResponse(PersonDetail personDetail);

    @AfterMapping
    default void linkBack(@MappingTarget PersonDetail person, PersonCreateRequest request) {
    }

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    void updatePersonDetailFromDTO(PersonDetailUpdateRequest personDetailUpdateRequest, @MappingTarget PersonDetail personDetail);
}
