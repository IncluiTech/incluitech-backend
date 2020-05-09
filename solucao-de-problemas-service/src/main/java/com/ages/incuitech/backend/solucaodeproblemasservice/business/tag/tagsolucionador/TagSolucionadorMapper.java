package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.tagsolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.TagSolucionadorResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class TagSolucionadorMapper {

    public static TagSolucionadorResponse mapToResponse(TagSolucionador tagSolucionador) {
        return TagSolucionadorResponse.builder()
                .id_tag(tagSolucionador.getIdTag())
                .id_solucionador(tagSolucionador.getIdSolucionador())
                .dataCriacao(LocalDateTime.now())
                .build();
    }
}
