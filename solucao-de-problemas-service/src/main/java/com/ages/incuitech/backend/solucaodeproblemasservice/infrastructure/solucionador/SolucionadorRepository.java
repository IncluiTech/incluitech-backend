package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SolucionadorRepository extends CrudRepository<Solucionador, Long> {

  @Query("SELECT * FROM Solucionador WHERE facebook_id = :facebookId")
  Solucionador findByIdFacebook(@Param("facebookId") String facebookId);
}
