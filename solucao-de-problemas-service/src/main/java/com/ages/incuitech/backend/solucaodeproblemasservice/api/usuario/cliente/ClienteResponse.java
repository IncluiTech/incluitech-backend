package com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.domain.Especialidade;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.domain.StatusCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private String nome;
    private String sobrenome;
    private String email;
    private List<Especialidade> especialidades;
    private Long id;
    private StatusCadastro statusCadastro;
}
