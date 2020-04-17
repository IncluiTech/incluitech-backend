package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import org.springframework.data.repository.CrudRepository;

public interface SolucionadorRepository extends CrudRepository<Solucionador, Long> {
}

