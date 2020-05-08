package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador.Tag_Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador.TagSolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long> {

    private TagService tagService;
    private TagSolucionadorService tag_solucionadorService;

    @Inject
    public void setRepository(SolucionadorRepository repository, TagService tagService, TagSolucionadorService tag_solucionadorService) {
        this.repository = repository;
        this.tagService = tagService;
        this.tag_solucionadorService = tag_solucionadorService;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        return this.findAll()
                .stream()
                .map(SolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public Solucionador salvar(SolucionadorRequest solucionadorRequest) {
        try {
            Solucionador solucionador = repository.save(SolucionadorMapper.mapToModel(solucionadorRequest));
            connectTagSolucionador(solucionador, solucionadorRequest.getTags());
            return repository.save(solucionador);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }

    private void connectTagSolucionador(Solucionador solucionador, List<String> tags) {
        for (String tag: tags) {
            Tag tagSalva = tagService.salvar(tag);
            tag_solucionadorService.salvar(Tag_Solucionador
                    .builder()
                    .id_solucionador(solucionador.getId())
                    .id_tag(tagSalva.getId())
                    .build());
        }
    }
}