package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.exception.ResourceNotFoundException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/cliente/")
public class ClienteController {

    private ClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("create")
    public ResponseEntity<ClienteResponse> inserirCliente(@RequestBody ClienteRequest clienteRequest){
        log.info("Salvando cliente: {}", clienteRequest);
        Cliente clienteSalvo = clienteService.salvar(ClienteMapper.mapToModel(clienteRequest));
        log.info("Cliente salvo: {}", clienteSalvo);
        return ResponseEntity.ok(ClienteMapper.mapToResponse(clienteSalvo));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarCliente(@PathVariable Long id){
        try {
            log.info("Iniciando busca por usuário com id {}", id);
            Cliente clienteSalvo = clienteService.buscar(id);
            log.info("Cliente com id {} encontrado", id);
            return ResponseEntity.ok(ClienteMapper.mapToResponse(clienteSalvo));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
