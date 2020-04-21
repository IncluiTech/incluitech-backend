package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.domain.StatusCadastro;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente mapToModel(ClienteRequest clienteRequest) {
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .telefone(clienteRequest.getTelefone())
                .tags(clienteRequest.getTags())
                .email(clienteRequest.getEmail())
                .statusCadastro(StatusCadastro.P)
                .build();
    }

    public static ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .nome(cliente.getNome())
                .id(cliente.getId())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .tags(cliente.getTags())
                .statusCadastro(cliente.getStatusCadastro())
                .build();
    }
}
