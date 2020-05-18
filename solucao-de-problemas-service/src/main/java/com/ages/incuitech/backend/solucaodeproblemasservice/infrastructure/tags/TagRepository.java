package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TagRepository  extends CrudRepository<Tag, Long> {

    @Query("SELECT * FROM tag WHERE nome = :nome")
    Optional<Tag> findByNome(@Param("nome") String nome);

}
