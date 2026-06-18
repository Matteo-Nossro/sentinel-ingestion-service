package com.sentinel.ingestion.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "metrics", schema = "ingestion")
public class MetricEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;
    private UUID sourceId;
    private String name;
    private double value;
    private Instant timestamp;

    public MetricEntity() {
    }

    public MetricEntity(UUID id, UUID sourceId, String name, double value, Instant timestamp) {
        this.id = id;
        this.sourceId = sourceId;
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSourceId() {
        return sourceId;
    }

    public void setSourceId(UUID sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

        
}
