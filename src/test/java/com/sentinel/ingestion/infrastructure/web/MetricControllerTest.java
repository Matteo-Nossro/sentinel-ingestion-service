package com.sentinel.ingestion.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sentinel.ingestion.application.command.IngestMetricHandler;
import com.sentinel.ingestion.domain.model.Metric;
import com.sentinel.ingestion.infrastructure.config.SecurityConfig;
import com.sentinel.ingestion.infrastructure.web.dto.MetricRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetricController.class)
@Import(SecurityConfig.class)
class MetricControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    IngestMetricHandler ingestMetricHandler;

    private MetricRequest validRequest() {
        return new MetricRequest(UUID.randomUUID(), "cpu_usage", 75.0);
    }

    @Test
    void ingestMetric_shouldReturn200WithResponse() throws Exception {
        Metric metric = Metric.create(UUID.randomUUID(), "cpu_usage", 75.0);
        when(ingestMetricHandler.handle(any())).thenReturn(metric);

        mockMvc.perform(post("/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("cpu_usage"))
                .andExpect(jsonPath("$.value").value(75.0));
    }

    @Test
    void ingestMetric_shouldReturn400WhenSourceIdIsNull() throws Exception {
        MetricRequest invalid = new MetricRequest(null, "cpu_usage", 75.0);

        mockMvc.perform(post("/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ingestMetric_shouldReturn400WhenNameIsBlank() throws Exception {
        MetricRequest invalid = new MetricRequest(UUID.randomUUID(), "", 75.0);

        mockMvc.perform(post("/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ingestMetric_shouldReturn400WhenValueIsNull() throws Exception {
        MetricRequest invalid = new MetricRequest(UUID.randomUUID(), "cpu_usage", null);

        mockMvc.perform(post("/metrics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }
}
