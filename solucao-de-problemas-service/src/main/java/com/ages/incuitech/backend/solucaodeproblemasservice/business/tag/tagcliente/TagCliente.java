package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagcliente;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
public class TagCliente {
    @Id
    private Long idTagCliente;
    private Long idTag;
    private Long idCliente;
    private LocalDateTime dataCriacao;
}
