package com.example.user_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "payment_token", nullable = false)
    private String paymentToken;

    @Column(name = "last_four_digits", length = 4, nullable = false)
    private String lastFourDigits;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;

    @Column(name = "cardholder_name", nullable = false)
    private String cardHolderName;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
