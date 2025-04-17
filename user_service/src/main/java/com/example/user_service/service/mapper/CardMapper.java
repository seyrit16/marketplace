package com.example.user_service.service.mapper;

import com.example.user_service.dto.request.card.CardLinkRequest;
import com.example.user_service.dto.response.card.CardResponse;
import com.example.user_service.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {

    Card fromCardLinkRequest(CardLinkRequest cardRequest);

    CardResponse toCardResponse(Card card);
}
