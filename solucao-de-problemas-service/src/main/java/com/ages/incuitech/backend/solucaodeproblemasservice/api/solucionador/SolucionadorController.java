package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import java.util.List;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorMapper;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.SolucionadorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("v1/solucionador")
public class SolucionadorController {

    private SolucionadorService service;

    SolucionadorController(SolucionadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolucionadorResponse> salvar(@RequestBody SolucionadorRequest solucionadorRequest) {
        log.info("Salvando solucionador: {}", solucionadorRequest);
        Solucionador solucionadorSalvo = service.salvar(SolucionadorMapper.mapToModel(solucionadorRequest));
        log.info("solucionador salvo: {}", solucionadorSalvo);
        return ResponseEntity.ok(SolucionadorMapper.mapToResponse(solucionadorSalvo));
    }

    @PutMapping
    public SolucionadorResponse updateFromFacebookId(@RequestBody SolucionadorRequest request) {
        return this.service.update(request);
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
