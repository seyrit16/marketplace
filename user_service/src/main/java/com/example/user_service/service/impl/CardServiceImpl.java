package com.example.user_service.service.impl;

import com.example.user_service.dto.request.card.CardLinkRequest;
import com.example.user_service.exception.ConflictResourceByUserException;
import com.example.user_service.exception.ResourceNotFoundException;
import com.example.user_service.model.Card;
import com.example.user_service.model.User;
import com.example.user_service.model.UserProfile;
import com.example.user_service.repository.CardRepository;
import com.example.user_service.repository.UserProfileRepository;
import com.example.user_service.service.CardService;
import com.example.user_service.service.UserService;
import com.example.user_service.service.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final CardMapper cardMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserService userService, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.userService = userService;
        this.cardMapper = cardMapper;
    }

    @Override
    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public void linkCardToUser(CardLinkRequest cardDTO) {

        UserProfile userProfile = userService.getUserProfileById(userService.getUserFromAuthentication().getId());
        Card card = cardMapper.fromCardLinkRequest(cardDTO);
        card.setUser(userProfile);

        userProfile.addCard(card);
        userService.saveUserProfile(userProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public Card getCardById(Long id) {

        Card card = cardRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Карта не найдена."));

        if(!card.getUser().getId().equals(userService.getUserFromAuthentication().getId())){
            throw new ConflictResourceByUserException("Карта не принадлежит данному пользователю");
        }

        return card;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCardsByUser() {

        return cardRepository.findAllByUserId(userService.getUserFromAuthentication().getId());
    }

    @Override
    @Transactional
    public void delete(Card card) {
        cardRepository.delete(card);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        delete(getCardById(id));
    }
}
