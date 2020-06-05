package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String funcao;
    private String instituicao;
    private List<String> tags;
    private StatusCadastro statusCadastro;
    private LocalDateTime dataCriacao;
}
