package com.sentinel.ingestion.domain.port.out;

import com.sentinel.ingestion.domain.model.Metric;

import java.util.List;
import java.util.UUID;

public interface MetricRepository {

    Metric save(Metric metric);

    List<Metric> findBySourceId(UUID sourceId);
}
