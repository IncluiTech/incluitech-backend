package com.ages.incuitech.backend.solucaodeproblemasservice.api.problema;

import lombok.Getter;

@Getter
public class ProblemaRequest {
    private String titulo;
    private String descricao;
    private Long idCliente;
}
