package com.example.user_service.dto.auth.request;

import com.example.user_service.dto.base.BaseUserDTO;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest extends BaseUserDTO {
    private String password;
    private String verifyCode;
}
