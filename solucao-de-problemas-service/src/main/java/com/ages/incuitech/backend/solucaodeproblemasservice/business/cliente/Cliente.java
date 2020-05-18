package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
public class Cliente {
  @Id private Long id;
  private String nome;
  private String telefone;
  private String email;
  private StatusCadastro statusCadastro;
  private LocalDateTime dataCriacao;
}
