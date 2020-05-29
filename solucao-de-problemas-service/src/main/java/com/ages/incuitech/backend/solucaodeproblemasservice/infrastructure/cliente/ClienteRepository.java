package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
    @Query("SELECT * FROM Cliente WHERE facebook_id = :facebookId")
    Cliente findByIdFacebook(@Param("facebookId") String facebookId);
    
    @Modifying
    @Query("UPDATE Cliente SET status_cadastro = 'A' WHERE facebook_id = :facebookId")
    int aprovarCadastro(String facebookId);

}

