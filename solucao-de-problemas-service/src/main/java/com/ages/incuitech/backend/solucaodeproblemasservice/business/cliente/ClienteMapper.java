package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.Problema;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaMapper;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ClienteMapper {

    public static Cliente mapToModel(ClienteRequest clienteRequest) {
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .telefone(clienteRequest.getTelefone())
                .email(clienteRequest.getEmail())
                .funcao(clienteRequest.getFuncao())
                .instituicao(clienteRequest.getInstituicao())
                .statusCadastro(StatusCadastro.P)
                .dataCriacao(LocalDateTime.now())
                .facebookId(clienteRequest.getFacebookId())
                .id(clienteRequest.getId())
                .build();
    }

    public static ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .nome(cliente.getNome())
                .id(cliente.getId())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .funcao(cliente.getFuncao())
                .instituicao(cliente.getInstituicao())
                .statusCadastro(cliente.getStatusCadastro())
                .dataCriacao(cliente.getDataCriacao())
                .build();
    }

    public static ClienteResponse mapToResponseWithTagsAndProblemas(Cliente cliente, List<String> tags, List<Problema> problemas) {
        if(problemas == null || problemas.isEmpty()) {
            return mapToResponse(cliente).withTags(tags).withProblemas(Collections.emptyList());
        }
        List<ProblemaResponse> problemasResponses = problemas.stream()
                .map(ProblemaMapper::fromModelToResponse)
                .collect(Collectors.toList());
        return mapToResponse(cliente).withTags(tags).withProblemas(problemasResponses);
    }

    public static ClienteResponse mapToResponseWithTags(Cliente cliente, List<String> tags) {
        return mapToResponse(cliente).withTags(tags);
    }
}
