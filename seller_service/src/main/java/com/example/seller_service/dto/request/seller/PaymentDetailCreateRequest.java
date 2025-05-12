package com.example.seller_service.dto.request.seller;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetailCreateRequest {
    @NotBlank(message = "Номер банковского счета не должен быть пустым")
    @Pattern(regexp = "\\d{20}", message = "Номер банковского счета должен содержать ровно 20 цифр")
    private String bankAccountNumber;
    @NotBlank(message = "Название банка не должно быть пустым")
    private String bankName;
    @NotBlank(message = "БИК не должен быть пустым")
    @Pattern(regexp = "\\d{9}", message = "БИК должен содержать ровно 9 цифр")
    private String bic;
    @NotBlank(message = "Имя владельца счета не должно быть пустым")
    private String accountHolderName;
    @NotBlank(message = "ИНН не должен быть пустым")
    @Pattern(regexp = "\\d{10}|\\d{12}", message = "ИНН должен содержать 10 или 12 цифр")
    private String inn;
}
