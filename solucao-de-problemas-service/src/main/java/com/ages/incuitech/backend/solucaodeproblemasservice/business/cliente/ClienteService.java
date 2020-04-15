package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;


import com.ages.incuitech.backend.solucaodeproblemasservice.api.exception.ResourceNotFoundException;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Cliente.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //@ToDo adicionar tratamento de excessão de banco
    public Cliente salvar(Cliente cliente) {
            return clienteRepository.save(cliente);
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente com id "+id+" não encontrado."));
    }
}
