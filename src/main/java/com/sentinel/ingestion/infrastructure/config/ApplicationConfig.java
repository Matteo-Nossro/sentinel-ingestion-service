package com.sentinel.ingestion.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sentinel.ingestion.application.command.IngestMetricHandler;
import com.sentinel.ingestion.application.command.RegisterSourceHandler;
import com.sentinel.ingestion.application.query.GetAllSourcesHandler;
import com.sentinel.ingestion.domain.port.in.IngestMetricUseCase;
import com.sentinel.ingestion.domain.port.in.RegisterSourceUseCase;
import com.sentinel.ingestion.domain.port.out.MetricEventPublisher;
import com.sentinel.ingestion.domain.port.out.MetricRepository;
import com.sentinel.ingestion.domain.port.out.SourceRepository;
import com.sentinel.ingestion.domain.service.IngestionDomainService;

@Configuration
public class ApplicationConfig {

    @Bean
    public IngestionDomainService ingestionDomainService(
            MetricRepository metricRepository,
            SourceRepository sourceRepository,
            MetricEventPublisher eventPublisher) {
        return new IngestionDomainService(metricRepository, sourceRepository, eventPublisher);
    }

    @Bean
    public IngestMetricHandler ingestMetricHandler(IngestMetricUseCase ingestMetricUseCase) {
        return new IngestMetricHandler(ingestMetricUseCase);
    }

    @Bean
    public RegisterSourceHandler registerSourceHandler(RegisterSourceUseCase registerSourceUseCase) {
        return new RegisterSourceHandler(registerSourceUseCase);
    }

    @Bean
    public GetAllSourcesHandler getAllSourcesHandler(SourceRepository sourceRepository) {
        return new GetAllSourcesHandler(sourceRepository);
    }
}
