package com.example.kafka.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RedisResponse {
    private String correlationId;
    private String operation;
    private String key;
    private Object value;
    private boolean success;
    private String error;
}
