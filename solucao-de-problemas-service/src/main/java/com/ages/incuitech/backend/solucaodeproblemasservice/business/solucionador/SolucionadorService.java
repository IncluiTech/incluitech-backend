package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagSolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {

    private TagService tagService;
    private TagSolucionadorRepository tagSolucionadorRepository;

    @Inject
    public void setRepository(SolucionadorRepository repository, TagService tagService, TagSolucionadorRepository tagSolucionadorRepository) {
        this.repository = repository;
        this.tagService = tagService;
        this.tagSolucionadorRepository = tagSolucionadorRepository;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        return this.findAll()
                .stream()
                .map(solucionador -> SolucionadorMapper.mapToResponseWithTags(solucionador, buscarTags(solucionador.getId())))
                .collect(Collectors.toList());
    }

    public SolucionadorResponse salvar(SolucionadorRequest solucionadorRequest) {
        try {
            Solucionador solucionadorSalvo = repository.save(SolucionadorMapper.mapToModel(solucionadorRequest));
            List<Tag> tags = salvarTags(solucionadorRequest.getTags());
            salvarTagsSolucionador(solucionadorSalvo.getId(), tags);
            return SolucionadorMapper.mapToResponseWithTags(solucionadorSalvo, TagMapper.mapToTagName(tags));
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
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

    private List<String> buscarTags(Long solucionadorId) {
        return tagSolucionadorRepository.findTagsOfSolucionador(solucionadorId).stream()
                .map(UserTag::getTagName)
                .collect(Collectors.toList());
    }

    private List<Tag> salvarTags(List<String> tags) {
        return tags.stream()
                .map(tagService::salvar)
                .collect(Collectors.toList());
    }

    private void salvarTagsSolucionador(Long solucionadorId, List<Tag> tags) {
        tagSolucionadorRepository.saveAll(tags.stream().map(tag ->
                TagSolucionador.builder()
                        .idTag(tag.getId())
                        .dataCriacao(LocalDateTime.now())
                        .idSolucionador(solucionadorId)
                        .build()
        ).collect(Collectors.toList()));
    }
}