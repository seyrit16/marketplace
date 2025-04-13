package com.example.user_service.dto.request.user;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdatePasswordRequest {
    String password;
}
