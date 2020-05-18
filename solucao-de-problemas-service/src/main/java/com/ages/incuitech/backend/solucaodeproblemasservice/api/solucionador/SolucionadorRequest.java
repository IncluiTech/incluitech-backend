package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Tag;
import java.util.List;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorRequest {
  @Setter private Long id;
  private String nome;
  private String telefone;
  private String email;
  private String lattes;
  private String facebookId;
  private List<Tag> tags;
}
