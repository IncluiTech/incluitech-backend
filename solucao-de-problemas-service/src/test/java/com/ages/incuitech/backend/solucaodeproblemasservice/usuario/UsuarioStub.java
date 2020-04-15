package com.ages.incuitech.backend.solucaodeproblemasservice.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.domain.StatusCadastro;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario.Usuario;

public class UsuarioStub {
    public static UsuarioRequest usuarioRequest() {
        return new UsuarioRequest("fulano");
    }

    public static Usuario usuario() {
        return Usuario.builder()
                .id(1L)
                .email("fulano@gmail.com")
                .nome("Fulano")
                .sobrenome("de Tal")
                .statusCadastro(StatusCadastro.P)
                .especialidades(null)
                .build();
    }
}
