package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.Tag_SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.tagSolucionador.Tag_SolucionadorResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class Tag_SolucionadorMapper {

    public static Tag_Solucionador mapToModel(Tag_SolucionadorRequest tag_solucionadorRequest) {
        return Tag_Solucionador
                .builder()
                .id_tag(tag_solucionadorRequest.getId_tag())
                .id_solucionador(tag_solucionadorRequest.getId_solucionador())
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static Tag_SolucionadorResponse mapToResponse(Tag_Solucionador tag_solucionador) {
        return Tag_SolucionadorResponse.builder()
                .id_tag(tag_solucionador.getId_tag())
                .id_solucionador(tag_solucionador.getId_solucionador())
                .dataCriacao(LocalDateTime.now())
                .build();
    }
}
