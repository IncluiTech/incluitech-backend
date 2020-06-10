package com.ages.incuitech.backend.solucaodeproblemasservice.business.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.problema.ProblemaRepository;
import org.springframework.stereotype.Service;

import static com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaMapper.fromRequestToModel;
import static com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaMapper.fromModelToResponse;

@Service
public class ProblemaService extends GenericCRUDService<Problema, Long, ProblemaRepository> {

    public ProblemaService(ProblemaRepository repository) {
        this.repository = repository;
    }

    public ProblemaResponse save(ProblemaRequest request) {
        return fromModelToResponse(this.save(fromRequestToModel(request)));
    }
}
