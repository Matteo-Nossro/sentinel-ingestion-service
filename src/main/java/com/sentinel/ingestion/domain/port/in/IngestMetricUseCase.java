package com.sentinel.ingestion.domain.port.in;
import com.sentinel.ingestion.domain.model.Metric;

public interface IngestMetricUseCase {
    public Metric ingest(Metric metric);
}
