package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClienteService extends GenericCRUDService<Cliente, Long> {

    @Inject
    public void setRepository(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> findAllClientes() {
        return this.findAll()
                .stream()
                .map(ClienteMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public Cliente salvar(Cliente cliente) {
        try {
            return repository.save(cliente);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Cliente: dados incorretos.");
            throw exception;
        } catch (Exception exception) {
            log.error("Erro ao salvar Cliente: {}", exception.toString());
            throw exception;
        }
    }
}
