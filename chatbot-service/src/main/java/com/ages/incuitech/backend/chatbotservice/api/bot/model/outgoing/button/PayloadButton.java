package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import lombok.*;

/**
 * Classe de botão representando postback, tipos de botões que enviam o seu {@code payload} de volta
 * para o bot
 *
 * @see Button
 */
@Getter
@Setter
@ToString
public class PayloadButton extends Button {
  private String payload;

  public PayloadButton(String titulo, String conteudo) {
    super(ButtonType.POSTBACK.getType(), titulo);
    this.payload = conteudo;
  }
}
