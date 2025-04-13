package com.example.user_service.config.security.components;

import com.example.user_service.invariant.Role;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtData {
    private Long id;
    private String email;
    private Role role;
    private LocalDateTime createdDateTime;
}
