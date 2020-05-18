package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

/**
 * Classe de botão que representa um botão do tipo link, ao invés do botão de postback, esse
 * direciona direto para a url contida nele.
 *
 * @see Button
 */
@Getter
@ToString
public class LinkButton extends Button {
  private String url;

  public LinkButton(String titulo, String url) {
    super(ButtonType.WEB_URL.getType(), titulo);
    this.url = url;
  }
}
