package com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.TagSolucionador.Tag_SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.TagSolucionador.Tag_SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador.Solucionador;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class Tag_SolucionadorMapper {

    public static Tag_Solucionador mapToModel(Tag_SolucionadorRequest tag_solucionadorRequest) {
        return Tag_Solucionador
                .builder()
                .id(tag_solucionadorRequest.getId())
                .id_tag(tag_solucionadorRequest.getId_tag())
                .id_solucionador(tag_solucionadorRequest.getId_solucionador())
                .statusCadastro(StatusCadastro.P)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public static Tag_SolucionadorResponse mapToResponse(Tag_Solucionador tag_solucionador) {
        return Tag_SolucionadorResponse.builder()
                .id(tag_solucionador.getId())
                .id_tag(tag_solucionador.getId_tag())
                .id_solucionador(tag_solucionador.getId_solucionador())
                .statusCadastro(tag_solucionador.getStatusCadastro())
                .dataCriacao(LocalDateTime.now())
                .build();
    }
}
