package com.example.user_service.dto.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class BaseUserDTO {
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
}
