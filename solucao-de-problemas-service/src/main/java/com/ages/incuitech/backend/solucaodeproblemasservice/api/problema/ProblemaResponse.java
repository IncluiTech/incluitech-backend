package com.ages.incuitech.backend.solucaodeproblemasservice.api.problema;

import lombok.Builder;

@Builder
public class ProblemaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private Long idCliente;
}
