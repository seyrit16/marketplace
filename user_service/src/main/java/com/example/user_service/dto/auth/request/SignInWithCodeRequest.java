package com.example.user_service.dto.auth.request;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignInWithCodeRequest {
    private String email;
    private String code;
}
