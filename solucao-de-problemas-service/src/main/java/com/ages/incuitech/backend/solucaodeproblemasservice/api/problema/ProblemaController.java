package com.ages.incuitech.backend.solucaodeproblemasservice.api.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
