package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.Especialidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    private String nome;
    private String sobrenome;
    private List<Especialidade> especialidades;
    private String email;
}
