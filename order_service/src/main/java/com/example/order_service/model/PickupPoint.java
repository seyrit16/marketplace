package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pickup_point")
public class PickupPoint {
    @Id
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "working_hours")
    private String workingHours;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "add_info",columnDefinition = "TEXT")
    private String addInfo;

    @OneToMany(mappedBy = "pickupPoint", cascade = CascadeType.PERSIST)
    private List<Order> orders;
}
