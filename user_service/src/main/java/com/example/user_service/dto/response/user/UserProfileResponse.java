package com.example.user_service.dto.response.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileResponse {
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;
}
