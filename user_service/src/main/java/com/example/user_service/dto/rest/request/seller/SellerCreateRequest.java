package com.example.user_service.dto.rest.request.seller;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SellerCreateRequest {
    private UUID id;

    @NotNull(message = "Данные контактного лица не должны быть пустыми")
    @Valid
    private PersonCreateRequest person;

    @NotBlank(message = "Полное название компании не должно быть пустым")
    private String fullCompanyName;

    @NotBlank(message = "Краткое название компании не должно быть пустым")
    private String shortCompanyName;

    private String description;

    @NotNull(message = "Платежные данные не должны быть пустыми")
    @Valid
    private PaymentDetailCreateRequest paymentDetail;
}
