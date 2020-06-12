package com.ages.incuitech.backend.chatbotservice.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProblemaDoCliente {
    private Long id;
    private String titulo;
    private String descricao;
    private Long idCliente;
}
