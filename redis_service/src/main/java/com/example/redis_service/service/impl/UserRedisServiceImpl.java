package com.example.redis_service.service.impl;

import com.example.kafka.dto.request.RedisRequest;
import com.example.kafka.dto.response.RedisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRedisServiceImpl<T> extends RedisServiceImpl<Object> {

    @Autowired
    public UserRedisServiceImpl(RedisTemplate<String, Object> redisTemplate, KafkaTemplate<String, Object> kafkaTemplate) {
        super(redisTemplate, kafkaTemplate);
    }

    @Override
    @KafkaListener(topics = "user-topic-save", groupId = "redis-group")
    void handleSave(@Payload RedisRequest request, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        try{
            System.out.println(request);
            save(request.getKey(),
                    request.getValue(),
                    request.getTimeout(),
                    request.getTimeUnit());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("save")
                            .key(key)
                            .value(null)
                            .success(true)
                            .error(null)
                            .build()
            );
        }catch (Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("save")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(Arrays.toString(exception.getStackTrace()))
                            .build());
            exception.printStackTrace();
        }
    }

    @Override
    @KafkaListener(topics = "user-topic-get", groupId = "redis-group")
    void handleGet(@Payload RedisRequest request,@Header(KafkaHeaders.RECEIVED_KEY)  String key) {
        try{
            Object value = get(request.getKey());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("get")
                            .key(key)
                            .value(value)
                            .success(true)
                            .error(null)
                            .build());
        }catch (Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("get")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(exception.getMessage())
                            .build());
        }
    }

    @Override
    @KafkaListener(topics = "user-topic-exists", groupId = "redis-group")
    void handleExists(@Payload RedisRequest request,@Header(KafkaHeaders.RECEIVED_KEY)  String key) {
        try{
            Object value = exists(request.getKey());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("exists")
                            .key(key)
                            .value(value)
                            .success(true)
                            .error(null)
                            .build());
        }catch (Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("exists")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(exception.getMessage())
                            .build());
        }
    }

    @Override
    @KafkaListener(topics = "user-topic-delete", groupId = "redis-group")
    void handleDelete(@Payload RedisRequest request,@Header(KafkaHeaders.RECEIVED_KEY)  String key) {
        try{
            delete(request.getKey());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("delete")
                            .key(key)
                            .value(null)
                            .success(true)
                            .error(null)
                            .build());
        }catch(Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("delete")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(exception.getMessage())
                            .build());
        }
    }

    @Override
    @KafkaListener(topics = "user-topic-save-to-hash", groupId = "redis-group")
    void handleSaveToHash(@Payload RedisRequest request,@Header(KafkaHeaders.RECEIVED_KEY)  String key) {
        try{
            saveToHash(request.getKey(), request.getHashKey(), request.getValue());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("save-to-hash")
                            .key(key)
                            .value(null)
                            .success(true)
                            .error(null)
                            .build());
        }catch (Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("save-to-hash")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(exception.getMessage())
                            .build());
        }
    }

    @Override
    @KafkaListener(topics = "user-topic-get-from-hash", groupId = "redis-group")
    void handleGetFromHash(@Payload RedisRequest request,@Header(KafkaHeaders.RECEIVED_KEY)  String key) {
        try{
            Object value = getFromHash(request.getKey(), request.getHashKey());
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("get-from-hash")
                            .key(key)
                            .value(value)
                            .success(true)
                            .error(null)
                            .build());
        }catch (Exception exception){
            kafkaTemplate.send("redis-response-topic", key,
                    RedisResponse.builder()
                            .correlationId(request.getCorrelationId())
                            .operation("get-from-hash")
                            .key(key)
                            .value(null)
                            .success(false)
                            .error(exception.getMessage())
                            .build());
        }
    }
}