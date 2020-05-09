package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TagSolucionador {
    @Id
    private Long idTagSolucionador;
    private Long idTag;
    private Long idSolucionador;
    private LocalDateTime dataCriacao;
}
