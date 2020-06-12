package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                    repository.save(buildTag(tag)));
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Tag: {}", exception.toString());
            throw exception;
        }
    }

    public List<Tag> batchSave(List<String> tags) {
        try{
            if(tags.isEmpty()) return Collections.emptyList();
            List<Tag> tagsExistentes = repository.encontraTodasTagsPelosNomes(tags);
            List<Tag> tagsParaCriar = tagsExistentes.stream()
                    .filter(it -> !tagsExistentes.contains(it))
                    .collect(Collectors.toList());
            repository.saveAll(tagsParaCriar);
            return tagsExistentes;
        }catch(DataAccessException exception){
            log.error("Erro ao salvar tags: {}", exception.toString());
            throw exception;
        }
    }

    private Tag buildTag(String tag) {
        return Tag.builder()
                .nome(tag)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    private Optional<Tag> buscarTagPorNome(String tag) {
        return repository.findByNome(tag);
    }

}