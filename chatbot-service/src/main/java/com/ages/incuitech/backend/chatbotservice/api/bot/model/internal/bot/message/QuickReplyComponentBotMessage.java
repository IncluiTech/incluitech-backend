package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 * Mensagem do tipo Quick Reply. Constituida por um texto e uma lista de botões de quick reply
 *
 * @see QuickReplyButton
 */
@Getter
@ToString
public class QuickReplyComponentBotMessage extends ComponentBotMessage {
  private String texto;
  private List<QuickReplyButton> quickReplyButtons;

  public QuickReplyComponentBotMessage(String texto, QuickReplyButton... quickReplyButtons) {
    super(ComponentBotMessageType.QUICK_REPLY);
    this.texto = texto;
    this.quickReplyButtons = Arrays.asList(quickReplyButtons);
  }

  public QuickReplyComponentBotMessage(String texto, List<QuickReplyButton> quickReplyButtons) {
    super(ComponentBotMessageType.QUICK_REPLY);
    this.texto = texto;
    this.quickReplyButtons = quickReplyButtons;
  }
}
