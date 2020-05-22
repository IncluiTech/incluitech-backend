package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorResponse {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String lattes;
    private List<String> tags;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
    private String facebookId;
}
