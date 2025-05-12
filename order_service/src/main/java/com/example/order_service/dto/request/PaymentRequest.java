package com.example.order_service.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    @NotNull(message = "Идентификатор заказа не должен быть пустым")
    private UUID orderId;
}
