package com.sentinel.ingestion.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sentinel.ingestion.infrastructure.persistence.entity.SourceEntity;

import java.util.UUID;

public interface SourceJpaRepository extends JpaRepository<SourceEntity, UUID> {

}
