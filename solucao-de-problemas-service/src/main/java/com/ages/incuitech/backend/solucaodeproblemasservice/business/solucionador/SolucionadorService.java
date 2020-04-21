package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long> {

    @Inject
    public void setRepository(SolucionadorRepository repository) {
        this.repository = repository;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        return this.findAll()
                .stream()
                .map(SolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    //@ToDo adicionar tratamento de excessão de banco
    public Solucionador salvar(Solucionador solucionador) {
        return repository.save(solucionador);
    }
}