package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
public class Cliente {
    @Id
    private final Long id;
    private final String nome;
    private final String telefone;
    private final String email;
    private final String funcao;
    private final String instituicao;
    private final StatusCadastro statusCadastro;
    private final LocalDateTime dataCriacao;
    private final String facebookId;
}
