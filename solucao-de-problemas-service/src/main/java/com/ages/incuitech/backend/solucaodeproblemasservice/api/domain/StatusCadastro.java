package com.ages.incuitech.backend.solucaodeproblemasservice.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCadastro {
    A("APROVADO"),
    P("PENDENTE_APROVACAO"),
    R("REPROVADO");

    private String texto;
}
