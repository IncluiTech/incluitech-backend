package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.*;
import lombok.extern.slf4j.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

import static java.util.Objects.*;

@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {

    private TagService tagService;
    private TagSolucionadorRepository tagSolucionadorRepository;
    private ChatBotClient client;

    public SolucionadorService(TagService tagService, TagSolucionadorRepository tagSolucionadorRepository, ChatBotClient client) {
        this.tagService = tagService;
        this.tagSolucionadorRepository = tagSolucionadorRepository;
        this.client = client;
    }

    @Inject
    public void setRepository(SolucionadorRepository repository) {
        this.repository = repository;
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

            if (isNull(solucionadorRequest.getTags())) {
                return SolucionadorMapper.mapToResponse(solucionadorSalvo);
            }

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

    public void aprovarCadastro(String facebookId) {
        this.repository.aprovarCadastro(facebookId);
        this.client.enviarMensagemCadastroSucesso(facebookId);
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