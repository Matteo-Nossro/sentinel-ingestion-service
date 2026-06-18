package com.sentinel.ingestion.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sentinel.ingestion.infrastructure.persistence.entity.MetricEntity;
import java.util.UUID;
import java.util.List;

public interface MetricJpaRepository extends JpaRepository<MetricEntity, UUID> {

    List<MetricEntity> findBySourceId(UUID sourceId);
    
}
