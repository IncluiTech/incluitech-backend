package com.ages.incuitech.backend.solucaodeproblemasservice.business.cliente;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.ClienteResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioResponse;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente mapToModel(UsuarioRequest usuarioRequest) {
        return Cliente.builder().nome(usuarioRequest.getNome()).build();
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
