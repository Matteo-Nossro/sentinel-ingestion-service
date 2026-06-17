package com.sentinel.ingestion.application.command;

import java.util.UUID;

public record IngestMetricCommand(UUID sourceId, String name, double value) {}
