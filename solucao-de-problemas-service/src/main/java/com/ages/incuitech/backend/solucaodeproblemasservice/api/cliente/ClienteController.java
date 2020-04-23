package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    private ClienteService clienteService;


    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> save(@RequestBody ClienteRequest clienteRequest){
        log.info("Salvando cliente: {}", clienteRequest);
        Cliente clienteSalvo = clienteService.salvar(ClienteMapper.mapToModel(clienteRequest));
        log.info("Cliente salvo: {}", clienteSalvo);
        return ResponseEntity.ok(ClienteMapper.mapToResponse(clienteSalvo));
    }

    @GetMapping
    public List<ClienteResponse> getAllClients() {
        return this.clienteService.findAllClientes();
    }
}
