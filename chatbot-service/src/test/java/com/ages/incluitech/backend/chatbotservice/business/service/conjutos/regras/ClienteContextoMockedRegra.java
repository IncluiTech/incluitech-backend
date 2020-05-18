package com.ages.incluitech.backend.chatbotservice.business.service.conjutos.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class ClienteContextoMockedRegra implements RegraDoBot {
  @Override
  public boolean verifica(MensagemInterna message) {
    Map<String, Object> contexto = message.getContexto();
    return contexto.containsKey("valor") && contexto.get("valor").equals("qualquerUm");
  }

  @Override
  public BotMessage processa(MensagemInterna message) {
    Map<String, Object> contexto = message.getContexto();
    contexto.put("tags", Arrays.asList("tag1", "tag2"));
    return new BotMessage(
        contexto, Collections.singletonList(new TextComponentBotMessage("Ok, temos suas tags")));
  }
}
