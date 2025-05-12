package com.example.order_service.dto.request.order;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderItemChangeStatusRequest {
    @NotNull(message = "Идентификатор элемента заказа не должен быть пустым")
    private UUID orderItemId;

    private String addInfo;
}
