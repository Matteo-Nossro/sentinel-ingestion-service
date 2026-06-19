package com.sentinel.ingestion.infrastructure.web;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.infrastructure.web.dto.MetricRequest;
import com.sentinel.ingestion.infrastructure.web.dto.MetricResponse;

import jakarta.validation.Valid;

import com.sentinel.ingestion.application.command.IngestMetricCommand;
import com.sentinel.ingestion.application.command.IngestMetricHandler;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final IngestMetricHandler metricHandler;

    public MetricController(IngestMetricHandler metricHandler) {
        this.metricHandler = metricHandler;
    }

    @PostMapping()
    public MetricResponse ingestMetric(@Valid @RequestBody MetricRequest metricRequest) {
        Metric response = metricHandler.handle(new IngestMetricCommand(
            metricRequest.sourceId(),
            metricRequest.name(),
            metricRequest.value()
        ));
        return new MetricResponse(
            response.id(),
            response.sourceId(),
            response.name(),
            response.value(),
            response.timestamp()
        );
    }

}
