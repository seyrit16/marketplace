package com.example.user_service.dto.response.user;

import com.example.user_service.dto.base.BaseUserDTO;
import com.example.user_service.invariant.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserResponse extends BaseUserDTO {
    private Role role;
    private Boolean isActive;
    private Boolean isLocked;

    private String token;
}
