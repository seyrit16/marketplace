package com.example.user_service.dto.base;

import com.example.user_service.dto.response.user.UserProfileResponse;
import com.example.user_service.dto.response.user.UserResponse;
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
    private UserProfileResponse userProfile;
}
