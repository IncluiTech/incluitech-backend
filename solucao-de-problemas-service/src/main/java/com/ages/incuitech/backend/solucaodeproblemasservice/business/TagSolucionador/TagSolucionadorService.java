package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.Tag_SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tag_solucionador.TagSolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagSolucionadorService extends GenericCRUDService<TagSolucionador, Long> {

    @Inject
    public void setRepository(TagSolucionadorRepository repository) {
        this.repository = repository;
    }

    public List<Tag_SolucionadorResponse> findAllTag_Solucionadores() {
        return this.findAll()
                .stream()
                .map(TagSolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public TagSolucionador salvar(TagSolucionador tag_solucionador) {
        try {
            return repository.save(tag_solucionador);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }
}