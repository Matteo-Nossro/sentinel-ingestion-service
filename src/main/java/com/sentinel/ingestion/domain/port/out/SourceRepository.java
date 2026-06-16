package com.sentinel.ingestion.domain.port.out;

import com.sentinel.ingestion.domain.model.Source;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SourceRepository {

    Source save(Source source);

    Optional<Source> findById(UUID id);

    List<Source> findAll();
}
