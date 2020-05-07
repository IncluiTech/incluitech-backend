package com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador;

import com.ages.incuitech.backend.chatbotservice.business.domain.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorRequest {
    private String nome;
    private String email;
    private String telefone;
    private String lattes;
    private List<Especialidade> especialidades;
    private String facebookId;
}
