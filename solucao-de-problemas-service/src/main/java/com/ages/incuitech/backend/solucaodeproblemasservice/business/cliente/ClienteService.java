package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteService extends GenericCRUDService<Cliente, Long, ClienteRepository> {

    private TagClienteRepository tagClienteRepository;

    @Inject
    public void setRepository(ClienteRepository repository, TagClienteRepository tagClienteRepository) {
        this.repository = repository;
        this.tagClienteRepository = tagClienteRepository;
    }

    public List<ClienteResponse> findAllClientes() {
        return this.findAll()
                .stream()
                .map(cliente -> ClienteMapper.mapToResponseWithTags(cliente, findTags(cliente.getId())))
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

    private List<String> findTags(Long clientId) {
        return tagClienteRepository.findTagsOfCliente(clientId).stream()
                .map(UserTag::getTagName)
                .collect(Collectors.toList());
    }
}
