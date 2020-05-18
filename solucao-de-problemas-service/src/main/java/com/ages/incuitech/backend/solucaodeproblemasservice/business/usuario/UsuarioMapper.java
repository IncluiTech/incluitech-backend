package com.ages.incuitech.backend.solucaodeproblemasservice.business.usuario;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.usuario.UsuarioResponse;

public class UsuarioMapper {

  public static Usuario mapToModel(UsuarioRequest usuarioRequest) {
    Usuario usuario = new Usuario();
    usuario.setNome(usuarioRequest.getNome());
    return usuario;
  }

  public static UsuarioResponse mapToResponse(Usuario usuario) {
    return new UsuarioResponse(usuario.getId(), usuario.getNome());
  }
}
