package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;

public class SolucionadorMapper {

    private SolucionadorMapper() {
    }

    public static Solucionador mapToModel(SolucionadorRequest solucionadorRequest) {
        return Solucionador
                .builder()
                .nome(solucionadorRequest.getNome())
                .build();
    }

    public static SolucionadorResponse mapToResponse(Solucionador solucionador) {
        return SolucionadorResponse.builder()
                .nome(solucionador.getNome())
                .sobrenome(solucionador.getSobrenome())
                .id(solucionador.getId())
                .email(solucionador.getEmail())
                .tags(solucionador.getTags())
                .statusCadastro(solucionador.getStatusCadastro())
                .build();
    }
}
