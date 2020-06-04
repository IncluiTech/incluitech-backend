package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import java.time.LocalDateTime;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@Setter
public class Solucionador {
    @Id
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String lattes;
    private String funcao;
    private String instituicao;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
    private String facebookId;
}
