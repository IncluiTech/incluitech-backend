package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tag_solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador.Tag_Solucionador;
import org.springframework.data.repository.CrudRepository;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;

public interface Tag_SolucionadorRepository extends CrudRepository<Tag_Solucionador, Long> {

}
