package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@Setter
public class Solucionador {
  @Id private Long id;
  private String nome;
  private String telefone;
  private String email;
  private String lattes;
  private LocalDateTime dataCriacao;
  private StatusCadastro statusCadastro;
  private String facebookId;
}
