package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class SolucionadorMapper {

    public static Solucionador mapToModel(SolucionadorRequest solucionadorRequest) {
        return Solucionador
                .builder()
                .nome(solucionadorRequest.getNome())
                .telefone(solucionadorRequest.getTelefone())
                .email(solucionadorRequest.getEmail())
                .lattes(solucionadorRequest.getLattes())
                .statusCadastro(StatusCadastro.P)
                .dataCriacao(LocalDateTime.now())
                .facebookId(solucionadorRequest.getFacebookId())
                .build();
    }

    public static SolucionadorResponse mapToResponse(Solucionador solucionador) {
        return SolucionadorResponse.builder()
                .id(solucionador.getId())
                .nome(solucionador.getNome())
                .telefone(solucionador.getTelefone())
                .email(solucionador.getEmail())
                .lattes(solucionador.getLattes())
                .statusCadastro(solucionador.getStatusCadastro())
                .dataCriacao(LocalDateTime.now())
                .facebookId(solucionador.getFacebookId())
                .build();
    }
}
