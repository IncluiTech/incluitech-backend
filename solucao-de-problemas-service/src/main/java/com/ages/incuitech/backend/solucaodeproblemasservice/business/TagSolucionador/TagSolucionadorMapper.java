package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.Tag_SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.Tag_SolucionadorResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class TagSolucionadorMapper {

    public static TagSolucionador mapToModel(Tag_SolucionadorRequest tag_solucionadorRequest) {
        return TagSolucionador
                .builder()
                .idTag(tag_solucionadorRequest.getId_tag())
                .idSolucionador(tag_solucionadorRequest.getId_solucionador())
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static Tag_SolucionadorResponse mapToResponse(TagSolucionador tag_solucionador) {
        return Tag_SolucionadorResponse.builder()
                .id_tag(tag_solucionador.getIdTag())
                .id_solucionador(tag_solucionador.getIdSolucionador())
                .dataCriacao(LocalDateTime.now())
                .build();
    }
}
