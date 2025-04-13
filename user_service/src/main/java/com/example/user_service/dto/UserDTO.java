package com.example.user_service.dto;

import com.example.user_service.dto.base.BaseUserDTO;
import com.example.user_service.invariant.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserDTO extends BaseUserDTO {
    private Long id;

    @JsonIgnore
    private String password;
    private Role role;
    private Long defaultPickupPointId;
    private Boolean isActive;
    private Boolean isLocked;

    private String verifyCode;
    private String token;
}
