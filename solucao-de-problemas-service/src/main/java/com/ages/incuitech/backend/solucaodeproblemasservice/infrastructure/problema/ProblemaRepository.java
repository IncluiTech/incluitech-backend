package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.Problema;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {

    @Query("SELECT * FROM Problema WHERE id_cliente = :idCliente")
    List<Problema> findByIdCliente(@Param("idCliente") Integer idCliente);
}
