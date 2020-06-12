package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.Problema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {

    public List<Problema> findByIdCliente(String idClient);
}
