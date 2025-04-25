package com.example.seller_service.dto.rest.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private String email;
    private Boolean isActive;
    private Boolean isLocked;
}
