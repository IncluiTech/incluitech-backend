package com.ages.incuitech.backend.chatbotservice.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProblemasCliente {
    CADASTRAR_PROBLEMAS("cadastrarProblema"),
    PROBLEMAS_EXISTENTES("verificarProblemas");
    private String caminho;

}
