package com.ages.incuitech.backend.solucaodeproblemasservice.api.problema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private Long idCliente;
}
