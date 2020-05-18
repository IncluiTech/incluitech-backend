package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioDaMensagem {
  private String id;
  private TipoUsuario tipoUsuario;

  public UsuarioDaMensagem(String id) {
    this.id = id;
  }
}
