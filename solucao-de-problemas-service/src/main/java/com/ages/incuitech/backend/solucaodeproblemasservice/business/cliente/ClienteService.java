package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.ChatBotClient;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.exception.ClienteNaoEncontradoException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.TagService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteService extends GenericCRUDService<Cliente, Long, ClienteRepository> {

    private TagService tagService;
    private TagClienteRepository tagClienteRepository;
    private ChatBotClient client;

    @Inject
    public void setRepository(ClienteRepository repository, TagClienteRepository tagClienteRepository) {
        this.repository = repository;
    }

    public List<ClienteResponse> findAllClientes() {
        Map<Long, List<String>> tags = buscarTodasAsTagsDosClientes();
        return this.findAll()
                .stream()
                .map(cliente -> ClienteMapper.mapToResponseWithTags(cliente, tags.get(cliente.getId())))
                .collect(Collectors.toList());
    }

    public Cliente salvar(Cliente cliente) {
        try {
            return repository.save(cliente);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Cliente: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Cliente: {}", exception.toString());
            throw exception;
        }
    }

    private Map<Long, List<String>> buscarTodasAsTagsDosClientes() {
        return tagClienteRepository.findAllLinkedTags().stream()
                .collect(groupingBy(UserTag::getUserId,
                        mapping(UserTag::getTagName, toList())));
    }

    private List<Tag> salvarTags(List<String> tags) {
        return tags.stream()
                .map(tagService::salvar)
                .collect(toList());
    }

    private void salvarTagsCliente(Long clienteId, List<Tag> tags) {
        tagClienteRepository.saveAll(TagMapper.buildTagCliente(tags, clienteId));
    }

    private List<Tag> persistirTagsCliente(ClienteRequest clienteRequest, Cliente clienteSalvo) {
        List<Tag> tags = salvarTags(clienteRequest.getTags());
        salvarTagsCliente(clienteSalvo.getId(), tags);
        return tags;
    }

    public ClienteResponse update(ClienteRequest request) {
        Cliente entity = this.repository.findByIdFacebook(request.getFacebookId());
        request.setId(entity.getId());
        Cliente updated = this.update(ClienteMapper.mapToModel(request));
        List<Tag> tags = this.persistirTagsCliente(request, entity);
        return ClienteMapper.mapToResponseWithTags(updated,TagMapper.mapToTagName(tags));
    }

    public void aprovarCadastro(String facebookId) {
        this.repository.aprovarCadastro(facebookId);
        this.client.enviarMensagemCadastroSucesso(facebookId);
    }

    public ClienteResponse findByFacebookId(String facecbookId) {
        Cliente cliente = this.repository.findByIdFacebook(facecbookId);
        if(cliente != null){
            return ClienteMapper.mapToResponse(cliente);
        }

        throw new ClienteNaoEncontradoException();
    }

}
