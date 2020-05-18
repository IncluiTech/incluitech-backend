package com.ages.incuitech.backend.chatbotservice.business.domain;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoContato {
  EMAIL("email"),
  TELEFONE("telefone"),
  EMAIL_E_TELEFONE("emailETelefone");
  private String propriedade;

  public static TipoContato getFromTexto(String texto) {
    return Arrays.stream(TipoContato.values())
        .filter(contato -> contato.propriedade.equals(texto))
        .findAny()
        .orElse(null);
  }
}
