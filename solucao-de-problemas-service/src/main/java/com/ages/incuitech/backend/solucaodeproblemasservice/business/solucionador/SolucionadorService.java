package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {

    private TagService tagService;
    private TagSolucionadorService tagSolucionadorService;

    @Inject
    public void setRepository(SolucionadorRepository repository, TagService tagService, TagSolucionadorService tagSolucionadorService) {
        this.repository = repository;
        this.tagService = tagService;
        this.tagSolucionadorService = tagSolucionadorService;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        return this.findAll()
                .stream()
                .map(SolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public SolucionadorResponse salvar(SolucionadorRequest solucionadorRequest) {
        try {
            Solucionador solucionadorSalvo = repository.save(SolucionadorMapper.mapToModel(solucionadorRequest));
            List<Tag> tagsSalvas = connectTagSolucionador(solucionadorSalvo, solucionadorRequest.getTags());
            return SolucionadorMapper.mapToResponse(solucionadorSalvo, tagsSalvas);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }


    private List<Tag> connectTagSolucionador(Solucionador solucionador, List<String> tags) {
        List<Tag> tagsSalvas = new ArrayList<>();
        Tag tagSalva;
        for (String tag : tags) {
            tagSalva = tagService.salvar(tag);
            tagsSalvas.add(tagSalva);
            tagSolucionadorService.salvar(TagSolucionador
                    .builder()
                    .idSolucionador(solucionador.getId())
                    .idTag(tagSalva.getId())
                    .dataCriacao(LocalDateTime.now())
                    .build());
        }
        return tagsSalvas;
    }


    public SolucionadorResponse findByFacebookId(String facecbookId) {
        Solucionador solucionador = this.repository.findByIdFacebook(facecbookId);
        return solucionador != null ? SolucionadorMapper.mapToResponse(solucionador) : null;
    }

    public SolucionadorResponse update(SolucionadorRequest request) {
        Solucionador entity = this.repository.findByIdFacebook(request.getFacebookId());
        request.setId(entity.getId());
        Solucionador updated = this.update(SolucionadorMapper.mapToModel(request));
        return SolucionadorMapper.mapToResponse(updated);
    }
}