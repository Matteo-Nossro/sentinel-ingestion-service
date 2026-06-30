package com.sentinel.ingestion.domain.service;

import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.domain.model.Source;
import com.sentinel.ingestion.domain.port.out.MetricEventPublisher;
import com.sentinel.ingestion.domain.port.out.MetricRepository;
import com.sentinel.ingestion.domain.port.out.SourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngestionDomainServiceTest {

    @Mock
    private MetricRepository metricRepository;

    @Mock
    private SourceRepository sourceRepository;

    @Mock
    private MetricEventPublisher eventPublisher;

    private IngestionDomainService service;

    @BeforeEach
    void setUp() {
        service = new IngestionDomainService(metricRepository, sourceRepository, eventPublisher);
    }

    @Test
    void ingest_shouldSaveMetricThenPublishEvent() {
        Metric metric = Metric.create(UUID.randomUUID(), "cpu_usage", 75.0);
        when(metricRepository.save(any())).thenReturn(metric);

        Metric result = service.ingest(metric);

        verify(metricRepository).save(metric);
        verify(eventPublisher).publish(metric);
        assertThat(result).isEqualTo(metric);
    }

    @Test
    void ingest_shouldPublishTheSavedMetricNotTheInput() {
        Metric input = Metric.create(UUID.randomUUID(), "cpu_usage", 75.0);
        Metric saved = Metric.create(input.sourceId(), "cpu_usage", 75.0);
        when(metricRepository.save(input)).thenReturn(saved);

        service.ingest(input);

        // s'assure que c'est bien la valeur retournée par save() qui est publiée
        verify(eventPublisher).publish(saved);
    }

    @Test
    void register_shouldSaveSourceAndReturnIt() {
        Source source = Source.create("prod-server-01", "Serveur de production");
        when(sourceRepository.save(any())).thenReturn(source);

        Source result = service.register(source);

        verify(sourceRepository).save(source);
        assertThat(result).isEqualTo(source);
    }
}
