package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags;

import org.springframework.data.repository.CrudRepository;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;

public interface TagRepository  extends CrudRepository<Solucionador, Long> {

}
