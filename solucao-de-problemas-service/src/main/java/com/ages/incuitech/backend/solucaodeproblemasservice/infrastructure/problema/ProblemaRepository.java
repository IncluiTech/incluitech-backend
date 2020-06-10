package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.Problema;
import org.springframework.data.repository.CrudRepository;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {
}
