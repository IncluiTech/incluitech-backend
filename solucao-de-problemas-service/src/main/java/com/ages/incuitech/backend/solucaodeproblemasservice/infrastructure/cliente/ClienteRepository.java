package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {}
