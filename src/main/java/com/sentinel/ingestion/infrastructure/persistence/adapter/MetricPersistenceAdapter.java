package com.sentinel.ingestion.infrastructure.persistence.adapter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sentinel.ingestion.infrastructure.persistence.repository.MetricJpaRepository;
import com.sentinel.ingestion.domain.port.out.MetricRepository;
import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.infrastructure.persistence.entity.MetricEntity;
import java.util.List;
import java.util.UUID;

@Component
public class MetricPersistenceAdapter implements MetricRepository {

    private final MetricJpaRepository metricJpaRepository;

    public MetricPersistenceAdapter(MetricJpaRepository metricJpaRepository) {
        this.metricJpaRepository = metricJpaRepository;
    }

    @Override
    public Metric save(Metric metric) {
        MetricEntity metricEntity = new MetricEntity();
        metricEntity.setId(metric.id());
        metricEntity.setSourceId(metric.sourceId());
        metricEntity.setName(metric.name());
        metricEntity.setValue(metric.value());
        metricEntity.setTimestamp(metric.timestamp());
        metricJpaRepository.save(metricEntity);
        return metric;
    }

    @Override
    public List<Metric> findBySourceId(UUID sourceId) {
        return metricJpaRepository.findBySourceId(sourceId).stream()
                .map(metricEntity -> new Metric(metricEntity.getId(), metricEntity.getSourceId(), metricEntity.getName(), metricEntity.getValue(), metricEntity.getTimestamp()))
                .toList();
    }

}
