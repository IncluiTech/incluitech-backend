package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import java.util.*;
import lombok.*;

/**
 * Classe represtantiva de uma mensagm do bot. Contem o contexto da conversa no momento que a
 * mensagem é gerada e um lista das mensagem que bot ira falar
 *
 * @see ComponentBotMessage
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@With
public class BotMessage {
  private final Map<String, Object> contexto;
  private List<ComponentBotMessage> messages = Collections.emptyList();

  public BotMessage withMessages(ComponentBotMessage... messages) {
    this.messages = new ArrayList<>(Arrays.asList(messages));
    return this;
  }
}
