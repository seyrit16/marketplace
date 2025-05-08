package com.example.order_service.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class OrderProduct {
    private UUID productId;
    private BigDecimal price;
}
