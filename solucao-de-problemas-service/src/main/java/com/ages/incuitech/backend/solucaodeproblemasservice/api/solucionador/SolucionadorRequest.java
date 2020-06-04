package com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolucionadorRequest {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String funcao;
    private String instituicao;
    private String lattes;
    private List<String> tags;
    private StatusCadastro statusCadastro;
    private String facebookId;
}
