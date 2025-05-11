package com.example.user_service.dto.request.card;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardLinkRequest {
    @NotBlank(message = "Токен платежа не должен быть пустым")
    String paymentToken;

    @NotBlank(message = "Последние четыре цифры карты не должны быть пустыми")
    @Pattern(regexp = "\\d{4}", message = "Последние четыре цифры карты должны содержать ровно 4 цифры")
    String lastFourDigits;

    @NotBlank(message = "Тип карты не должен быть пустым")
    String cardType;

    @NotBlank(message = "Дата истечения карты не должна быть пустой")
    @Pattern(regexp = "(0[1-9]|1[0-2])/\\d{2}", message = "Дата истечения карты должна быть в формате MM/YY")
    String expiryDate;

    @NotBlank(message = "Имя держателя карты не должно быть пустым")
    String cardHolderName;

    @NotNull(message = "Флаг по умолчанию не должен быть пустым")
    Boolean isDefault = false;
}
