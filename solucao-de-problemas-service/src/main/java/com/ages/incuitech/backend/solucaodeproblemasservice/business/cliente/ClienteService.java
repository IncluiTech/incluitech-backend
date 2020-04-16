package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
}