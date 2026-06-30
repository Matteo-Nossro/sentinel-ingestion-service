package com.sentinel.ingestion.application.command;

import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.domain.port.in.IngestMetricUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngestMetricHandlerTest {

    @Mock
    private IngestMetricUseCase ingestMetricUseCase;

    @InjectMocks
    private IngestMetricHandler handler;

    @Test
    void handle_shouldBuildMetricFromCommandAndDelegate() {
        UUID sourceId = UUID.randomUUID();
        IngestMetricCommand command = new IngestMetricCommand(sourceId, "cpu_usage", 85.5);
        when(ingestMetricUseCase.ingest(any())).thenAnswer(inv -> inv.getArgument(0));

        handler.handle(command);

        ArgumentCaptor<Metric> captor = ArgumentCaptor.forClass(Metric.class);
        verify(ingestMetricUseCase).ingest(captor.capture());
        Metric metric = captor.getValue();
        assertThat(metric.sourceId()).isEqualTo(sourceId);
        assertThat(metric.name()).isEqualTo("cpu_usage");
        assertThat(metric.value()).isEqualTo(85.5);
        assertThat(metric.id()).isNotNull();
        assertThat(metric.timestamp()).isNotNull();
    }

    @Test
    void handle_shouldReturnMetricFromUseCase() {
        UUID sourceId = UUID.randomUUID();
        Metric expected = Metric.create(sourceId, "cpu_usage", 85.5);
        when(ingestMetricUseCase.ingest(any())).thenReturn(expected);

        Metric result = handler.handle(new IngestMetricCommand(sourceId, "cpu_usage", 85.5));

        assertThat(result).isEqualTo(expected);
    }
}
