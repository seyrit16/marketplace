package com.example.user_service.service;

import com.example.user_service.config.security.components.JwtData;
import com.example.user_service.dto.request.card.CardLinkRequest;
import com.example.user_service.model.Card;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardService {

    Card save(Card card);

    void linkCardToUser(CardLinkRequest cardDTO);

    Card getCardById(Long id);

    List<Card> getAllCardsByUser();

    void delete(Card card);

    void deleteById(Long id);
}
