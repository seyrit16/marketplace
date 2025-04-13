package com.example.kafka.dto.request;

import lombok.*;

import java.util.concurrent.TimeUnit;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RedisRequest {
    private String correlationId;
    private String key;
    private Object value;
    private String hashKey;
    private Long timeout;
    private TimeUnit timeUnit;
}
