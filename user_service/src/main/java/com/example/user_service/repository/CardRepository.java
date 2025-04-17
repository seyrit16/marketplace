package com.example.user_service.repository;

import com.example.user_service.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUserId(UUID userId);
}
