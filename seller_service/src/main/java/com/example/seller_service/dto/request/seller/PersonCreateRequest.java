package com.example.seller_service.dto.request.seller;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonCreateRequest {
    @NotBlank(message = "Фамилия не должна быть пустой")
    private String surname;

    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    private String patronimyc;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(regexp = "\\+?[1-9]\\d{1,14}", message = "Номер телефона должен быть валидным")
    private String phoneNumber;
}
