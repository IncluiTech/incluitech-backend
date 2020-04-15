package com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.cliente.ClienteResponse;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario mapToModel(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        return usuario;
    }

    public static UsuarioResponse mapToResponse(Usuario usuario) {
        return new UsuarioResponse(usuario.getId(), usuario.getNome());
    }

    public static ClienteResponse mapToClienteResponse(Usuario usuario) {
        return ClienteResponse.builder()
                .nome(usuario.getNome())
                .id(usuario.getId())
                .email(usuario.getEmail())
                .especialidades(usuario.getEspecialidades())
                .statusCadastro(usuario.getStatusCadastro())
                .build();
    }
}
