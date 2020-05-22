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
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private StatusCadastro statusCadastro;
    private LocalDateTime dataCriacao;
    private String facebookId;
}
