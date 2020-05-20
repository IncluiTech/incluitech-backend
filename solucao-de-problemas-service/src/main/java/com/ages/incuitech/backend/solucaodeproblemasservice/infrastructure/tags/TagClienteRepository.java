package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.TagCliente;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente.UserTag;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagClienteRepository extends CrudRepository<TagCliente, Long> {

    @Query("SELECT t.nome as tagName, t as userId " +
            "FROM tag_cliente tc " +
            "JOIN tag t on t.id = tc.id_tag " +
            "WHERE tc.id_cliente = :clientId")
    List<UserTag> findTagsOfCliente(@Param("clientId") Long clientId);
}