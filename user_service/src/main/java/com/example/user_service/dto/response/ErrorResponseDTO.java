package com.example.user_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseDTO {
    private int status;
    private String error;
    private String message;
    private String timestamp;
}
