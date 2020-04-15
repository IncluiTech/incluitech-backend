package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("select id, nome from cliente where nome like :nome")
    Optional<Cliente> buscarPorNome(@Param("name") String nome);

}
