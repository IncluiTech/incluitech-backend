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
                .especialidades(clienteRequest.getEspecialidades())
                .sobrenome(clienteRequest.getSobrenome())
                .email(clienteRequest.getEmail())
                .statusCadastro(StatusCadastro.P)
                .build();
    }

    public static ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .nome(cliente.getNome())
                .sobrenome(cliente.getSobrenome())
                .id(cliente.getId())
                .email(cliente.getEmail())
                .especialidades(cliente.getEspecialidades())
                .statusCadastro(cliente.getStatusCadastro())
                .build();
    }
}
