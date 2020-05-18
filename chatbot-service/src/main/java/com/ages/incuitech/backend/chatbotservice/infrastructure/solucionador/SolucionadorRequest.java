package com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador;

import com.ages.incuitech.backend.chatbotservice.business.domain.Especialidade;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorRequest implements Serializable {
  private Long id;
  private String nome;
  private String telefone;
  private String email;
  private String lattes;
  private String facebookId;
  private List<Especialidade> especialidades;
}
