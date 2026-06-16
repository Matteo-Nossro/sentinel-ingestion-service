package com.sentinel.ingestion.domain.model;

import java.util.UUID;
import java.util.Optional;

public record Source(UUID id,  String name, Optional<String> description) {


    public static Source create(String name, String description ) {
        return new Source(UUID.randomUUID(), name,  Optional.ofNullable(description));
    }

}
