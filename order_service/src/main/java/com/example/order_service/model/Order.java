package com.example.order_service.model;

import com.example.order_service.model.embeddable.OrderProduct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    @CollectionTable(name = "order_products", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderProduct> productsIds;

    @Column(name = "full_price", nullable = false)
    private BigDecimal fullPrice;

    @ManyToOne
    @JoinColumn(name = "pickup_point_id", nullable = false)
    private PickupPoint pickupPoint;
}
