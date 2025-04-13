package com.example.redis_service.service.impl;

import com.example.kafka.dto.request.RedisRequest;
import com.example.kafka.dto.response.RedisResponse;
import com.example.redis_service.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public abstract class RedisServiceImpl<T> implements RedisService<T> {
    private final RedisTemplate<String, Object> redisTemplate;
    protected final KafkaTemplate<String, Object> kafkaTemplate;

    private final ConcurrentHashMap<String, CompletableFuture<RedisResponse>> responseFuture = new ConcurrentHashMap<>();

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate, KafkaTemplate<String, Object> kafkaTemplate) {
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    abstract void handleSave(RedisRequest request, String key);
    abstract void handleGet(RedisRequest request, String key);
    abstract void handleExists(RedisRequest request, String key);
    abstract void handleDelete(RedisRequest request, String key);
    abstract void handleSaveToHash(RedisRequest request, String key);
    abstract void handleGetFromHash(RedisRequest request, String key);



    @Override
    public void save(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void saveToHash(String key, String hashKey, T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getFromHash(String key, String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }
}
