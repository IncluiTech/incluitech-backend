package com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    private String nome;
    private String senha;
    private String sobrenome;
    private String especialidades;
    private String email;
}
