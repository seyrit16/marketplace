package com.example.order_service.dto.request.address;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressCreateRequest {
    @NotBlank(message = "Регион не должен быть пустым")
    private String region;

    @NotBlank(message = "Город не должен быть пустым")
    private String city;

    @NotBlank(message = "Улица не должна быть пустой")
    private String street;

    @NotBlank(message = "Номер дома не должен быть пустым")
    private String houseNumber;

    @NotBlank(message = "Почтовый индекс не должен быть пустым")
    @Pattern(regexp = "\\d{5,6}", message = "Почтовый индекс должен содержать 5 или 6 цифр")
    private String postalCode;
}
