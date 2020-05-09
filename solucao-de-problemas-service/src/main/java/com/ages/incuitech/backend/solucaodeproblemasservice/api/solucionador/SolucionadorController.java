package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<SolucionadorResponse> getAllSolucionadores() {
        return this.service.findAllSolucionadores();
    }

    @GetMapping("/{facebookId}")
    public SolucionadorResponse getByFacebookId(@PathVariable("facebookId") String facebookId) {
        return this.service.findByFacebookId(facebookId);
    }
}
