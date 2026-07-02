package com.assignment.sms_sender.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void blockNumber(String phoneNumber) {
        redisTemplate.opsForValue().set(phoneNumber, "BLOCKED");
    }

    public boolean isBlocked(String phoneNumber) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(phoneNumber));
    }
}