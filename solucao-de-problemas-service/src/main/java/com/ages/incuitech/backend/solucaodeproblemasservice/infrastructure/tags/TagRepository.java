package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags;

import org.springframework.data.repository.CrudRepository;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

public interface TagRepository  extends CrudRepository<Tag, Long> {

}
