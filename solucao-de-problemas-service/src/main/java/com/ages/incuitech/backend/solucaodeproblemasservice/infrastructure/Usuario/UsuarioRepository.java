package com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.Usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
  @Query("select id,nome from usuario where nome like :nome")
  Optional<Usuario> buscarPorName(@Param("name") String nome);
}
