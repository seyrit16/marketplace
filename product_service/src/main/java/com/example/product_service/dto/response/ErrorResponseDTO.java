package com.example.product_service.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorResponseDTO {
    private int status;
    private String error;
    private List<String> errors;
    private String message;
    private String timestamp;
}
