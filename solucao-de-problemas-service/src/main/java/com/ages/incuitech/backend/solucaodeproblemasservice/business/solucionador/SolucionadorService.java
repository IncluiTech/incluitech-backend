package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.*;
import lombok.extern.slf4j.*;
import org.springframework.dao.*;
import org.springframework.stereotype.*;

import javax.inject.*;
import java.util.*;

import static com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorMapper.*;
import static java.util.Objects.*;
import static java.util.stream.Collectors.*;


@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {

    private TagService tagService;
    private TagSolucionadorRepository tagSolucionadorRepository;
    private ChatBotClient client;


    public SolucionadorService(TagService tagService, TagSolucionadorRepository tagSolucionadorRepository, SolucionadorRepository solucionadorRepository, ChatBotClient client) {
        this.repository = solucionadorRepository;
        this.tagService = tagService;
        this.tagSolucionadorRepository = tagSolucionadorRepository;
        this.client = client;
    }

    @Inject
    public void setRepository(SolucionadorRepository repository) {
        this.repository = repository;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        Map<Long, List<String>> tags = buscarTodasAsTagsDosSolucionadores();
        return this.findAll()
                .stream()
                .map(solucionador -> mapToResponseWithTags(solucionador, tags.get(solucionador.getId())))
                .collect(toList());
    }

    public SolucionadorResponse salvar(SolucionadorRequest solucionadorRequest) {
        try {
            Solucionador solucionadorSalvo = repository.save(SolucionadorMapper.mapToModel(solucionadorRequest));

            if (isNull(solucionadorRequest.getTags())) {
                return SolucionadorMapper.mapToResponse(solucionadorSalvo);
            }
            List<Tag> tags = persistirTagsSolucionador(solucionadorRequest, solucionadorSalvo);
            return mapToResponseWithTags(solucionadorSalvo, TagMapper.mapToTagName(tags));
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }

    private List<Tag> persistirTagsSolucionador(SolucionadorRequest solucionadorRequest, Solucionador solucionadorSalvo) {
        List<Tag> tags = salvarTags(solucionadorRequest.getTags());
        salvarTagsSolucionador(solucionadorSalvo.getId(), tags);
        return tags;
    }

    public SolucionadorResponse findByFacebookId(String facecbookId) {
        Solucionador solucionador = this.repository.findByIdFacebook(facecbookId);
        return solucionador != null ? SolucionadorMapper.mapToResponse(solucionador) : null;
    }

    public SolucionadorResponse update(SolucionadorRequest request) {
        Solucionador entity = this.repository.findByIdFacebook(request.getFacebookId());
        request.setId(entity.getId());
        Solucionador updated = this.update(SolucionadorMapper.mapToModel(request));
        List<Tag> tags = this.persistirTagsSolucionador(request, entity);
        return mapToResponseWithTags(updated, TagMapper.mapToTagName(tags));
    }

    public void aprovarCadastro(String facebookId) {
        this.repository.aprovarCadastro(facebookId);
        this.client.enviarMensagemCadastroSucesso(facebookId);
    }

    private List<Tag> salvarTags(List<String> tags) {
        return tags.stream()
                .map(tagService::salvar)
                .collect(toList());
    }

    private void salvarTagsSolucionador(Long solucionadorId, List<Tag> tags) {
        tagSolucionadorRepository.saveAll(TagMapper.buildTagSolucionador(tags, solucionadorId));
    }

    private Map<Long, List<String>> buscarTodasAsTagsDosSolucionadores() {
        return this.tagSolucionadorRepository.findAllLinkedTags().stream()
                .collect(groupingBy(UserTag::getUserId,
                        mapping(UserTag::getTagName, toList())));
    }

    public List<SolucionadorResponse> findCadastroPendente() {
        List<Solucionador> pendentesAprovacao = this.repository.findByStatusCadastro(StatusCadastro.P.name());
        Map<Long, List<String>> solucionadorTagMap = this.buscarTodasAsTagsDosSolucionadores();
        return pendentesAprovacao.stream()
                .map(solucionador -> mapToResponseWithTags(solucionador, solucionadorTagMap.get(solucionador.getId())))
                .collect(toList());
    }
}