package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class Cliente {
    @Id
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String funcao;
    private String instituicao;
    private StatusCadastro statusCadastro;
    private LocalDateTime dataCriacao;
    private String facebookId;
}
