package com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("v1/client")
public class ClienteController {

    private ClienteService service;

    ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponse> getAllClients() {
        return this.service.findAllClientes();
    }
}
