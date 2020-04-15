package com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.UsuarioMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService extends GenericCRUDService<Usuario, Long> {

    @Inject
    public void setRepository(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> findAllClientes() {
        return this.findAll().stream().map(UsuarioMapper::mapToClienteResponse).collect(Collectors.toList());
    }

}
