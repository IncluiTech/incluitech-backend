package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String funcao;
    private String instituicao;
    private List<String> tags;
    private String facebookId;
}
