package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
}
