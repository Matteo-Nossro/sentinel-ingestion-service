package com.sentinel.ingestion.domain.service;

import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.domain.model.Source;
import com.sentinel.ingestion.domain.port.in.IngestMetricUseCase;
import com.sentinel.ingestion.domain.port.in.RegisterSourceUseCase;
import com.sentinel.ingestion.domain.port.out.MetricEventPublisher;
import com.sentinel.ingestion.domain.port.out.MetricRepository;
import com.sentinel.ingestion.domain.port.out.SourceRepository;

public class IngestionDomainService implements IngestMetricUseCase, RegisterSourceUseCase {
    private final MetricRepository metricRepository;        
    private final SourceRepository sourceRepository;        
    private final MetricEventPublisher eventPublisher;      

    public IngestionDomainService(
        MetricRepository metricRepository,
        SourceRepository sourceRepository,
        MetricEventPublisher eventPublisher
    ) {
        this.metricRepository = metricRepository;
        this.sourceRepository = sourceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Metric ingest(Metric metric) {
        Metric savedMetric = metricRepository.save(metric);
        eventPublisher.publish(savedMetric);
        return savedMetric;
    }

    @Override
    public Source register(String name, String description) {
        Source newSource = Source.create(name, description); 
        sourceRepository.save(newSource);
        return newSource;
    }
}
