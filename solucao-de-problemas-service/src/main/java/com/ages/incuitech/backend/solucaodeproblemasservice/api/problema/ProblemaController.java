package com.ages.incuitech.backend.solucaodeproblemasservice.api.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/problema")
public class ProblemaController {

    private ProblemaService service;

    public ProblemaController(ProblemaService service) {
        this.service = service;
    }

    @PostMapping
    public ProblemaResponse save(@RequestBody ProblemaRequest request) {
        return this.service.save(request);
    }

    @GetMapping("/{clientId}")
    public List<ProblemaResponse> problemas(@PathVariable("clientId") Integer clientId) {
        return this.service.findAllOf(clientId);
    }
}
