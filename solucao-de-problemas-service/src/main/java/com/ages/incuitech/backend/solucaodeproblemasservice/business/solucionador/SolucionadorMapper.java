package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.tag.Tag;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SolucionadorMapper {

    public static Solucionador mapToModel(SolucionadorRequest solucionadorRequest) {
        return Solucionador
                .builder()
                .id(solucionadorRequest.getId())
                .nome(solucionadorRequest.getNome())
                .telefone(solucionadorRequest.getTelefone())
                .email(solucionadorRequest.getEmail())
                .lattes(solucionadorRequest.getLattes())
                .funcao(solucionadorRequest.getFuncao())
                .instituicao(solucionadorRequest.getInstituicao())
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
                .funcao(solucionador.getFuncao())
                .instituicao(solucionador.getInstituicao())
                .statusCadastro(solucionador.getStatusCadastro())
                .dataCriacao(solucionador.getDataCriacao())
                .facebookId(solucionador.getFacebookId())
                .build();
    }

    public static SolucionadorResponse mapToResponseWithTags(Solucionador solucionador, List<String> tags) {
        return mapToResponse(solucionador).withTags(tags);
    }
}
