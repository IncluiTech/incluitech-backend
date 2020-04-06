package com.ages.incuitech.backend.chatbotservice.usuario;

import com.ages.incuitech.backend.chatbotservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.chatbotservice.business.usuario.Usuario;

public class UsuarioStub {
    public static UsuarioRequest usuarioRequest() {
        return new UsuarioRequest("fulano");
    }

    public static Usuario usuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        usuario.setNome("fulano");
        return usuario;
    }
}
