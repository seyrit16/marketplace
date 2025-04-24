package com.example.user_service.dto.request.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileUpdateRequest {
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
}
