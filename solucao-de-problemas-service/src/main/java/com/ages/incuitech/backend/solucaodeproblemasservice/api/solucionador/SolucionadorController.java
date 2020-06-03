package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.exception.SolucionadorNaoEncontradoException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.exception.ClienteNaoEncontradoException;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("v1/solucionador")
public class SolucionadorController {

    private SolucionadorService service;

    public SolucionadorController(SolucionadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolucionadorResponse> salvar(@RequestBody SolucionadorRequest solucionadorRequest) {
        log.info("Salvando solucionador: {}", solucionadorRequest);
        SolucionadorResponse solucionadorSalvo = service.salvar(solucionadorRequest);
        log.info("solucionador salvo: {}", solucionadorSalvo);
        return ResponseEntity.ok(solucionadorSalvo);
    }

    @PutMapping
    public SolucionadorResponse updateFromFacebookId(@RequestBody SolucionadorRequest request) {
        return this.service.updateAndNotificarADM(request);
    }

    @PutMapping("/{facebookId}")
    public ResponseEntity<SolucionadorRequest> aprovarCadastro(@PathVariable("facebookId") String facebookId) {
        this.service.aprovarCadastro(facebookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<SolucionadorResponse> getAllSolucionadores() {
        return this.service.findAllSolucionadores();
    }

    @GetMapping("/pendente")
    public List<SolucionadorResponse> getSolucionadoresCadastroPendente() {
        return this.service.findCadastroPendente();
    }

    @GetMapping("/{facebookId}")
    public ResponseEntity<SolucionadorResponse> getByFacebookId(@PathVariable("facebookId") String facebookId) {
        try{
            return ResponseEntity.ok(this.service.findByFacebookId(facebookId));
        }catch (SolucionadorNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
