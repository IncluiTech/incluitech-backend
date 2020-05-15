package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import java.time.LocalDateTime;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
    private String facebookId;
}
