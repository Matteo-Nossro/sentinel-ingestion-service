package com.sentinel.ingestion.infrastructure.messaging;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import  com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.domain.port.out.MetricEventPublisher;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class RedisMetricEventPublisher implements MetricEventPublisher {

    private final StringRedisTemplate  redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisMetricEventPublisher(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void publish(Metric event) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            redisTemplate.convertAndSend("metric.events", eventJson);
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish metric event to Redis", e);
        }
    }
    
}
