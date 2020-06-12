package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.ClienteService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.exception.ClienteNaoEncontradoException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/cliente")
public class ClienteController {

    private ClienteService clienteService;
    private ProblemaService problemaService;

    public ClienteController(ClienteService clienteService, ProblemaService problemaService) {
        this.clienteService = clienteService;
        this.problemaService = problemaService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> salvar(@RequestBody ClienteRequest clienteRequest){
        log.info("Salvando cliente: {}", clienteRequest);
        Cliente clienteSalvo = clienteService.salvar(ClienteMapper.mapToModel(clienteRequest));
        log.info("Cliente salvo: {}", clienteSalvo);
        return ResponseEntity.ok(ClienteMapper.mapToResponse(clienteSalvo));
    }

    @GetMapping
    public List<ClienteResponse> getAllClients() {
        return this.clienteService.findAllClientes();
    }

    @PutMapping
    public ClienteResponse updateFromFacebookId(@RequestBody ClienteRequest request) {
        return this.clienteService.update(request);
    }

    @PutMapping("/{facebookId}")
    public ResponseEntity<ClienteRequest> aprovarCadastro(@PathVariable("facebookId") String facebookId) {
        this.clienteService.aprovarCadastro(facebookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{clientId}/problemas")
    public List<ProblemaResponse> problemas(@PathVariable("clientId") Integer clientId) {
        return this.problemaService.findAllOf(clientId);
    }

    @GetMapping("/{facebookId}")
    public ResponseEntity<ClienteResponse> getByFacebookId(@PathVariable("facebookId") String facebookId) {
        try{
            return ResponseEntity.ok(this.clienteService.findByFacebookId(facebookId));
        }catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
