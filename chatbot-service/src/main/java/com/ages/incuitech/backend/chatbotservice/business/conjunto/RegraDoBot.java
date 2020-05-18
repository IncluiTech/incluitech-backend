package com.ages.incuitech.backend.chatbotservice.business.conjunto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;

public interface RegraDoBot {
  boolean verifica(MensagemInterna message);

  BotMessage processa(MensagemInterna message);
}
