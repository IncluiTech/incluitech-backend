package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class TagService extends GenericCRUDService<Tag, Long, TagRepository> {

    @Inject
    public void setRepository(TagRepository repository) {
        this.repository = repository;
    }
 
    public Tag salvar(String tag) {
        try {
            Optional<Tag> tagOptional = buscarTagPorNome(tag);
            return tagOptional.orElseGet(() ->
                    repository.save(Tag.builder()
                            .nome(tag)
                            .dataCriacao(LocalDateTime.now())
                            .build()));
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Tag: {}", exception.toString());
            throw exception;
        }
    }

    private Optional<Tag> buscarTagPorNome(String tag) {
        return repository.findByNome(tag);
	}

}