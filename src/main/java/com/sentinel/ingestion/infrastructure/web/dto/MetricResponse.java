package com.sentinel.ingestion.infrastructure.web.dto;

import java.util.UUID;
import java.time.Instant;

public record MetricResponse(
    UUID id,
    UUID sourceId,
    String name,
    Double value,
    Instant timestamp
) {
}
