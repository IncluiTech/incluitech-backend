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
                .telefone(solucionadorRequest.getTelefone())
                .email(solucionadorRequest.getEmail())
                .lattes(solucionadorRequest.getLattes())
                .tags(solucionadorRequest.getTags())
                .build();
    }

    public static SolucionadorResponse mapToResponse(Solucionador solucionador) {
        return SolucionadorResponse.builder()
                .id(solucionador.getId())
                .nome(solucionador.getNome())
                .telefone(solucionador.getTelefone())
                .email(solucionador.getEmail())
                .tags(solucionador.getTags())
                .statusCadastro(solucionador.getStatusCadastro())
                .build();
    }
}
