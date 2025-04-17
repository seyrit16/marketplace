package com.example.user_service.service.impl;

import com.example.kafka.dto.request.RedisRequest;
import com.example.kafka.dto.response.RedisResponse;
import com.example.user_service.exception.VerificationServiceWorkException;
import com.example.user_service.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationServiceImpl implements VerificationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<RedisResponse>> responseFutures =
            new ConcurrentHashMap<>();
    private static final String KEY_PREFIX = "verification:email:";

    @Value("${verification.code.code_ttl_minutes}")
    private Long CODE_TTL_MINUTES;
    private static final long ACCEPTABLE_WAITING_TIME_SECONDS = 10;

    @Autowired
    public VerificationServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void saveVerificationCode(String email, String code) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            // отправка запроса
            String key = KEY_PREFIX + email;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .value(code)
                    .timeout(CODE_TTL_MINUTES)
                    .timeUnit(TimeUnit.MINUTES)
                    .build();
            kafkaTemplate.send("user-topic-save", key, request);

            // получение ответа
            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);
            if (!response.isSuccess()) {
                throw new VerificationServiceWorkException("Ошибка при получении ответа от Redis " +
                        "(saveVerificationCode)\n"+response);
            }
        } catch (VerificationServiceWorkException verificationServiceWorkException) {
            responseFutures.remove(correlationId);
            throw new VerificationServiceWorkException(verificationServiceWorkException.getMessage());
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to save verification code: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @Override
    public Boolean verifyCode(String email, String code) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            String key = KEY_PREFIX + email;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .build();
            kafkaTemplate.send("user-topic-get", key, request);

            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);


            if (Optional.ofNullable(response.getValue()).isEmpty()) {
                return false;
            }
            if (!response.isSuccess()) {
                throw new VerificationServiceWorkException("Ошибка при получении ответа от Redis (verifyCode)");
            }

            return code.equals(response.getValue());

        } catch (VerificationServiceWorkException verificationServiceWorkException) {
            responseFutures.remove(correlationId);
            throw new VerificationServiceWorkException(verificationServiceWorkException.getMessage());
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to verify verification code: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @Override
    public void deleteCode(String email) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            String key = KEY_PREFIX + email;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .build();
            kafkaTemplate.send("user-topic-delete", key, request);

            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);

            if (!response.isSuccess()) {
                throw new VerificationServiceWorkException("Ошибка при получении ответа от Redis (verifyCode)");
            }
        } catch (VerificationServiceWorkException verificationServiceWorkException) {
            responseFutures.remove(correlationId);
            throw new VerificationServiceWorkException(verificationServiceWorkException.getMessage());
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to delete verification code: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @KafkaListener(topics = "redis-response-topic", groupId = "user-group")
    public void handleRedisResponse(RedisResponse response) {
        CompletableFuture<RedisResponse> future = responseFutures.get(response.getCorrelationId());
        if (future != null) {
            future.complete(response);
        }
    }
}
