package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.ChatBotClient;
import com.ages.incuitech.backend.solucaodeproblemasservice.MailService;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.exception.SolucionadorNaoEncontradoException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.adm.AdministradoresService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagSolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorMapper.mapToResponseWithTags;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;


@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {
    private TagService tagService;
    private TagSolucionadorRepository tagSolucionadorRepository;
    private ChatBotClient client;
    private MailService mailService;
    private AdministradoresService admService;

    public SolucionadorService(
            TagService tagService,
            TagSolucionadorRepository tagSolucionadorRepository,
            SolucionadorRepository solucionadorRepository,
            ChatBotClient client,
            MailService mailService,
            AdministradoresService admService
    ) {
        this.repository = solucionadorRepository;
        this.tagService = tagService;
        this.tagSolucionadorRepository = tagSolucionadorRepository;
        this.client = client;
        this.mailService = mailService;
        this.admService = admService;
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
        if (solucionador != null) return SolucionadorMapper.mapToResponse(solucionador);
        else throw new SolucionadorNaoEncontradoException();
    }

    public SolucionadorResponse updateAndNotificarADM(SolucionadorRequest request) {
        SolucionadorResponse response = this.update(request);
        this.notificarAdministradorCadastroSucesso();
        return response;
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
        return tagService.batchSave(tags);
    }

    private void salvarTagsSolucionador(Long solucionadorId, List<Tag> tags) {
        List<UserTag> currentTags = tagSolucionadorRepository.findTagsOfSolucionador(solucionadorId);
        List<String> currentTagsNames = currentTags.stream().map(UserTag::getTagName).collect(toList());
        List<Tag> newTags = tags.stream().filter(tag -> !currentTagsNames.contains(tag.getNome())).collect(toList());
        tagSolucionadorRepository.saveAll(TagMapper.buildTagSolucionador(newTags, solucionadorId));
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

    private void notificarAdministradorCadastroSucesso() {
        this.mailService.sendTo(this.admService.getEmails(), "Novo solucionador cadastrado!", "Olá!" +
                "\nGostaríamos de informar que um novo solucionador se cadastrou no sistema da IncluiTec e está aguardando" +
                "a sua aprovação no painel administrativo.");
    }

    public List<SolucionadorResponse> findCadastroAprovado() {
        List<Solucionador> aprovados = this.repository.findByStatusCadastro(StatusCadastro.A.name());
        Map<Long, List<String>> solucionadorTagMap = this.buscarTodasAsTagsDosSolucionadores();
        return aprovados.stream()
                .map(solucionador -> mapToResponseWithTags(solucionador, solucionadorTagMap.get(solucionador.getId())))
                .collect(toList());
    }
}