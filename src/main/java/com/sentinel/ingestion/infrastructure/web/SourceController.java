package com.sentinel.ingestion.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.sentinel.ingestion.application.command.RegisterSourceHandler;
import com.sentinel.ingestion.application.command.RegisterSourceCommand;
import com.sentinel.ingestion.infrastructure.web.dto.SourceRequest;
import com.sentinel.ingestion.infrastructure.web.dto.SourceResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.sentinel.ingestion.application.query.GetAllSourcesHandler;
import com.sentinel.ingestion.application.query.GetAllSourcesQuery;
import jakarta.validation.Valid;
import com.sentinel.ingestion.domain.model.Source;


@RestController
@RequestMapping("/sources")
public class SourceController {
    
    private final RegisterSourceHandler registerSourceHandler;
    private final GetAllSourcesHandler getAllSourcesHandler;


    public SourceController(RegisterSourceHandler registerSourceHandler, GetAllSourcesHandler getAllSourcesHandler) {
        this.registerSourceHandler = registerSourceHandler;
        this.getAllSourcesHandler = getAllSourcesHandler;
    }

    @PostMapping()
    public SourceResponse save(@Valid @RequestBody SourceRequest sourceRequest) {
        Source saved = registerSourceHandler.handle(new RegisterSourceCommand(
        sourceRequest.name(),
        sourceRequest.description()
));
return new SourceResponse(saved.id(), saved.name(), saved.description().orElse(null));

    }

    @GetMapping()
    public List<SourceResponse> getSources() {
        return getAllSourcesHandler.handle(new GetAllSourcesQuery()).stream().map(source -> new SourceResponse(
            source.id(),
            source.name(),
            source.description().orElse(null)
        )).toList();
    }
}
    



