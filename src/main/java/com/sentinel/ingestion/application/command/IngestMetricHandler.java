package com.sentinel.ingestion.application.command;

import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.domain.port.in.IngestMetricUseCase;

public class IngestMetricHandler {

    private final IngestMetricUseCase ingestMetricUseCase;

    public IngestMetricHandler(IngestMetricUseCase ingestMetricUseCase) {
        this.ingestMetricUseCase = ingestMetricUseCase;
    }

    public Metric handle(IngestMetricCommand command) {
        Metric metric = Metric.create(command.sourceId(), command.name(), command.value());
        return ingestMetricUseCase.ingest(metric);
    }
}
