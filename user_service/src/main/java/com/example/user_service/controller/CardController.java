package com.example.user_service.controller;

import com.example.user_service.dto.request.card.CardLinkRequest;
import com.example.user_service.dto.response.Response;
import com.example.user_service.dto.response.card.CardResponse;
import com.example.user_service.model.Card;
import com.example.user_service.service.CardService;
import com.example.user_service.service.mapper.CardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/card")
@Validated
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    public CardController(CardService cardService, CardMapper cardMapper) {
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @GetMapping("/get/by_id")
    public ResponseEntity<CardResponse> getCardById(@Positive(message = "Идентификатор карты должен быть положительным числом")
                                                    @RequestParam(name = "id") Long id) {
        Card card = cardService.getCardById(id);
        CardResponse cardResponse = cardMapper.toCardResponse(card);
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardResponse);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CardResponse>> getAllCards() {
        List<Card> cardList = cardService.getAllCardsByUser();
        List<CardResponse> cardResponseList = cardList.stream()
                .map(cardMapper::toCardResponse)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(cardResponseList);
    }

    @PostMapping("/link_to_user")
    public ResponseEntity<Void> linkToUser(@Valid @RequestBody CardLinkRequest cardLinkRequest) {
        cardService.linkCardToUser(cardLinkRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCardById(@Positive(message = "Идентификатор карты должен быть положительным числом")
                                               @RequestParam(name = "id") Long id) {
        cardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}