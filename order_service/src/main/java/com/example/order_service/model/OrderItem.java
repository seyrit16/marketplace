package com.example.order_service.model;

import com.example.order_service.model.invariant.OrderItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "quantity",nullable = false)
    private Integer quantity;

    @Column(name = "item_price", nullable = false)
    private BigDecimal itemPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", nullable = false)
    private OrderItemStatus itemStatus;

    @Column(name = "add_info_for_status", columnDefinition = "TEXT")
    private String addInfoForStatus;
}
