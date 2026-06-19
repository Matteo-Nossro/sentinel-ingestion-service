package com.sentinel.ingestion.infrastructure.web.dto;

import java.util.UUID;

public record SourceResponse(
    UUID id,
    String name,
    String description
) {
}
