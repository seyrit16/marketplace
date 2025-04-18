package com.example.seller_service.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "person_details")
public class PersonDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerProfile seller;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "patronimyc")
    private String patronimyc;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
}
