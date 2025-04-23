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
@Table(name = "payment_details")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerProfile seller;

    @Column(name = "bank_account_number", nullable = false)
    private String bankAccountNumber;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bic", nullable = false)
    private String bic;

    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Column(name = "inn")
    private String inn;

    @Column(name = "is_verified")
    private Boolean isVerified = false;
}
