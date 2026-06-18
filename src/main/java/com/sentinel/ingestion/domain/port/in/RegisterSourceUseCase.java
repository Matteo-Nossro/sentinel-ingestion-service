package com.sentinel.ingestion.domain.port.in;

import com.sentinel.ingestion.domain.model.Source;

public interface RegisterSourceUseCase {

    Source register(Source source);
}
