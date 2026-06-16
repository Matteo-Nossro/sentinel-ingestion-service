package com.sentinel.ingestion.domain.port.out;

import com.sentinel.ingestion.domain.model.Metric;

public interface MetricEventPublisher {

    void publish(Metric metric);
}
