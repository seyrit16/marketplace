package com.example.redis_service.service;

import java.util.concurrent.TimeUnit;

public interface RedisService<T> {
    /**
     * Сохраняет значение в Redis с указанным ключом и временем жизни.
     *
     * @param key     Ключ
     * @param value   Значение типа T
     * @param timeout Время жизни
     * @param unit    Единица времени
     */
    void save(String key, T value, long timeout, TimeUnit unit);

    /**
     * Получает значение по ключу.
     *
     * @param key Ключ
     * @return Значение типа T или null, если ключ не существует
     */
    T get(String key);

    /**
     * Проверяет, существует ли ключ.
     *
     * @param key Ключ
     * @return true, если ключ существует
     */
    boolean exists(String key);

    /**
     * Удаляет ключ.
     *
     * @param key Ключ
     */
    void delete(String key);

    /**
     * Сохраняет значение в хэш Redis.
     *
     * @param key      Ключ хэша
     * @param hashKey  Ключ внутри хэша
     * @param value    Значение типа T
     */
    void saveToHash(String key, String hashKey, T value);

    /**
     * Получает значение из хэша.
     *
     * @param key     Ключ хэша
     * @param hashKey Ключ внутри хэша
     * @return Значение типа T или null
     */
    T getFromHash(String key, String hashKey);
}