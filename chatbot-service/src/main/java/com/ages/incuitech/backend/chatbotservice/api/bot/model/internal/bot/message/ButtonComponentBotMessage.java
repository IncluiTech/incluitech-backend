package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import java.util.List;
import lombok.Getter;

/**
 * Classe representando uma mensagem em que possui um conjunto de botões. Contem um texto que vem
 * previamente da lista de botões e uma lista de botões
 *
 * @see Button
 */
@Getter
public class ButtonComponentBotMessage extends ComponentBotMessage {
  private String texto;
  private List<Button> botoes;

  public ButtonComponentBotMessage(String texto, List<Button> botoes) {
    super(ComponentBotMessageType.BOTAO);
    this.texto = texto;
    this.botoes = botoes;
  }
}
