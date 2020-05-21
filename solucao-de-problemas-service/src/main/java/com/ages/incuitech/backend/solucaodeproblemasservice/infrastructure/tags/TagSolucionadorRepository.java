package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador.TagSolucionador;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagSolucionadorRepository extends CrudRepository<TagSolucionador, Long> {

    @Query("SELECT t.nome as tag_name, ts.id_solucionador as user_id " +
            "FROM tag_solucionador ts " +
            "JOIN tag t on t.id = ts.id_tag " +
            "WHERE ts.id_solucionador = :solucionadorId")
    List<UserTag> findTagsOfSolucionador(@Param("solucionadorId") Long solucionadorId);


    @Query("SELECT ts.id_solucionador as user_id, t.nome as tag_name " +
            "FROM tag_solucionador ts " +
            "JOIN tag t on ts.id_tag  = t.id")
    List<UserTag> findAllLinkedTags();
}
