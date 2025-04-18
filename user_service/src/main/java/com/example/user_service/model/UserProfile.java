package com.example.user_service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();

    @Column(name = "default_pickup_point_id")
    private Long defaultPickupPointId;

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
