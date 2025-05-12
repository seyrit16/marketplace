package com.example.seller_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "sellers")
public class SellerProfile {
    @Id
    private UUID id;

    @Column(name = "user_id",nullable = false)
    private UUID userId;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    @JoinColumn(name = "person_detail_id",nullable = false)
    private PersonDetail personDetail;

    @Column(name = "full_company_name",nullable = false)
    private String fullCompanyName;

    @Column(name = "short_company_name",nullable = false)
    private String shortCompanyName;

    @Column(name = "is_verified",nullable = false)
    private Boolean isVerified=false;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_payment_detail_id")
    private PaymentDetail paymentDetail;
}
