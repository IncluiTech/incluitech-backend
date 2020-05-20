package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagSolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class TagSolucionadorService extends GenericCRUDService<TagSolucionador, Long, TagSolucionadorRepository> {

    @Inject
    public void setRepository(TagSolucionadorRepository repository) {
        this.repository = repository;
    }

    public TagSolucionador salvar(TagSolucionador tagSolucionador) {
        try {
            return repository.save(tagSolucionador);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }
}