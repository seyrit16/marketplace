package com.example.order_service.service.impl;

import com.example.kafka.dto.request.RedisRequest;
import com.example.kafka.dto.response.RedisResponse;
import com.example.order_service.dto.request.CartRequest;
import com.example.order_service.dto.response.CartResponse;
import com.example.order_service.exception.CartServiceWorkException;
import com.example.order_service.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CartServiceImpl implements CartService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<RedisResponse>> responseFutures =
            new ConcurrentHashMap<>();
    private static final String KEY_PREFIX = "cart:userid:";

    @Value("${cart.ttl_value}")
    private Long CODE_TTL_VALUE;
    private static final long ACCEPTABLE_WAITING_TIME_SECONDS = 10;

    private final ObjectMapper objectMapper;

    @Autowired
    public CartServiceImpl(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void saveCart(UUID userId, CartRequest cart) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            // отправка запроса
            String key = KEY_PREFIX + userId;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .value(cart)
                    .timeout(CODE_TTL_VALUE)
                    .timeUnit(TimeUnit.DAYS)
                    .build();
            kafkaTemplate.send("order-topic-save", key, request);

            // получение ответа
            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);
            if (!response.isSuccess()) {
                throw new CartServiceWorkException("Ошибка при получении ответа от Redis " +
                        "(saveCart)\n" + response);
            }
        } catch (CartServiceWorkException cartServiceWorkException) {
            responseFutures.remove(correlationId);
            throw new CartServiceWorkException(cartServiceWorkException.getMessage());
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to save cart: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @Override
    public CartResponse getCart(UUID id) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            String key = KEY_PREFIX + id;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .build();
            kafkaTemplate.send("order-topic-get", key, request);

            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);


            if (Optional.ofNullable(response.getValue()).isEmpty() || !response.isSuccess()) {
                return null;
            }

            return objectMapper.convertValue(response.getValue(), CartResponse.class);
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to get cart: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @Override
    public void deleteCart(UUID id) {

        String correlationId = UUID.randomUUID().toString();
        CompletableFuture<RedisResponse> future = new CompletableFuture<>();
        responseFutures.put(correlationId, future);
        try {
            String key = KEY_PREFIX + id;
            RedisRequest request = RedisRequest.builder()
                    .correlationId(correlationId)
                    .key(key)
                    .build();
            kafkaTemplate.send("order-topic-delete", key, request);

            RedisResponse response = future.get(ACCEPTABLE_WAITING_TIME_SECONDS, TimeUnit.SECONDS);
            responseFutures.remove(correlationId);
            if (!response.isSuccess()) {
                throw new CartServiceWorkException("Ошибка при получении ответа от Redis " +
                        "(deleteCart)\n" + response);
            }
        } catch (CartServiceWorkException cartServiceWorkException) {
            responseFutures.remove(correlationId);
            throw new CartServiceWorkException(cartServiceWorkException.getMessage());
        } catch (Exception exception) {
            responseFutures.remove(correlationId);
            throw new RuntimeException("Failed to delete cart: " +
                    (exception.getMessage() != null ? exception.getMessage() : exception.toString()), exception);
        }
    }

    @KafkaListener(topics = "redis-response-topic", groupId = "order-group")
    public void handleRedisResponse(RedisResponse response) {
        CompletableFuture<RedisResponse> future = responseFutures.get(response.getCorrelationId());
        if (future != null) {
            future.complete(response);
        }
    }
}
