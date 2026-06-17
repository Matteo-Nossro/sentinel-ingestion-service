package com.sentinel.ingestion.application.command;

import com.sentinel.ingestion.domain.model.Source;
import com.sentinel.ingestion.domain.port.in.RegisterSourceUseCase;

public class RegisterSourceHandler {
    private final RegisterSourceUseCase registerSourceUseCase;

    public RegisterSourceHandler(RegisterSourceUseCase registerSourceUseCase) {
        this.registerSourceUseCase = registerSourceUseCase;
    }

    public Source handle(RegisterSourceCommand command) {
        return registerSourceUseCase.register(command.name(), command.description());
    }
}
