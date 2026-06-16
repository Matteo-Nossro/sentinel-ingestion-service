package com.sentinel.ingestion.domain.model;

import java.time.Instant;
import java.util.UUID;

public record Metric(UUID id, UUID sourceId, String name, double value, Instant timestamp) {

    public static Metric create(UUID sourceId, String name, double value) {
        return new Metric(UUID.randomUUID(), sourceId, name, value, Instant.now());
    }
}
