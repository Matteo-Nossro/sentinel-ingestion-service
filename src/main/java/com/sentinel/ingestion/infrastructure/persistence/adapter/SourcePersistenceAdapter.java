package com.sentinel.ingestion.infrastructure.persistence.adapter;

import com.sentinel.ingestion.domain.port.out.SourceRepository;
import com.sentinel.ingestion.infrastructure.persistence.repository.SourceJpaRepository;

import com.sentinel.ingestion.infrastructure.persistence.entity.SourceEntity;
import com.sentinel.ingestion.domain.model.Source;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SourcePersistenceAdapter implements SourceRepository {

    private final SourceJpaRepository sourceJpaRepository;

    public SourcePersistenceAdapter(SourceJpaRepository sourceJpaRepository) {
        this.sourceJpaRepository = sourceJpaRepository;
    }

    @Override
    public Source save(Source source) {
        SourceEntity sourceEntity = new SourceEntity();
        sourceEntity.setId(source.id());
        sourceEntity.setName(source.name());
        sourceEntity.setDescription(source.description().orElse(null));
        sourceJpaRepository.save(sourceEntity);

        return source;
    }

    @Override
    public Optional<Source> findById(UUID id) {
        return sourceJpaRepository.findById(id).map(sourceEntity -> new Source(sourceEntity.getId(), sourceEntity.getName(), Optional.ofNullable(sourceEntity.getDescription())));
    }

    @Override
    public List<Source> findAll() {
        List<SourceEntity> sourceEntities = sourceJpaRepository.findAll();
        return sourceEntities.stream()
                .map(sourceEntity ->new Source(sourceEntity.getId(), sourceEntity.getName(), Optional.ofNullable(sourceEntity.getDescription())))
                .toList();
    }

   
    
}
