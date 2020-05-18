package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.Getter;
import lombok.ToString;

/**
 * Classe Abstrada mãe de todos os possiveis tipo de mensagem que o bot pode enviar o atributo
 * {@code private ComponentBotMessageType type} representa o tipo da mensagem
 *
 * @see QuickReplyComponentBotMessage
 * @see TextComponentBotMessage
 * @see ButtonComponentBotMessage
 * @see CarrouselComponentBotMessage
 */
@Getter
@ToString
public abstract class ComponentBotMessage {
  private ComponentBotMessageType type;

  public ComponentBotMessage(ComponentBotMessageType type) {
    this.type = type;
  }
}
