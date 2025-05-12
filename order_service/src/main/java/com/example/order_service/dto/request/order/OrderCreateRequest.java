package com.example.order_service.dto.request.order;

import com.example.order_service.model.PickupPoint;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCreateRequest {
    @NotNull(message = "Идентификатор пункта выдачи не должен быть пустым")
    private UUID pickupPointId;

    @NotEmpty(message = "Список элементов заказа не должен быть пустым")
    @Valid
    private List<OrderItemCreateRequest> items;
}
