package com.ages.incuitech.backend.solucaodeproblemasservice.business.problema;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Builder
@Getter
public class Problema {
    @Id
    private Long id;
    private String titulo;
    private String descricao;
    private Long idCliente;

}
