package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente mapToModel(ClienteRequest clienteRequest) {
        return Cliente.builder().nome(clienteRequest.getNome()).build();
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
