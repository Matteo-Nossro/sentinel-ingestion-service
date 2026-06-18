package com.sentinel.ingestion.application.query;

import com.sentinel.ingestion.domain.model.Source;
import com.sentinel.ingestion.domain.port.out.SourceRepository;

import java.util.List;

public class GetAllSourcesHandler {

    private final SourceRepository sourceRepository;

    public GetAllSourcesHandler(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    public List<Source> handle(GetAllSourcesQuery query) {
        return sourceRepository.findAll();
    }
}
