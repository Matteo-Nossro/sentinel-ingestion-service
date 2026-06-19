package com.sentinel.ingestion.infrastructure.web.dto;


import jakarta.validation.constraints.NotBlank;

public record SourceRequest(
    @NotBlank String name,
    String description
) {
}
