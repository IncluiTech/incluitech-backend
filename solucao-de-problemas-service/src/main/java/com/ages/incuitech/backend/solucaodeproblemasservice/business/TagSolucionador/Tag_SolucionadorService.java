package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.TagSolucionador.Tag_SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tag_solucionador.Tag_SolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Tag_SolucionadorService extends GenericCRUDService<Tag_Solucionador, Long> {

    @Inject
    public void setRepository(Tag_SolucionadorRepository repository) {
        this.repository = repository;
    }

    public List<Tag_SolucionadorResponse> findAllTag_Solucionadores() {
        return this.findAll()
                .stream()
                .map(Tag_SolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public Tag_Solucionador salvar(Tag_Solucionador tag_solucionador) {
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