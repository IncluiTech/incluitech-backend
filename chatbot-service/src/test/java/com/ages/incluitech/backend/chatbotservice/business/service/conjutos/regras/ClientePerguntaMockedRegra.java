package com.ages.incluitech.backend.chatbotservice.business.service.conjutos.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.ButtonComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.PayloadButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import java.util.Collections;
import java.util.HashMap;

public class ClientePerguntaMockedRegra implements RegraDoBot {
  @Override
  public boolean verifica(MensagemInterna message) {
    return message.getTipo().equals(TipoMensagem.BOTAO);
  }

  @Override
  public BotMessage processa(MensagemInterna message) {
    return new BotMessage(
        new HashMap<>(),
        Collections.singletonList(
            new ButtonComponentBotMessage(
                "Texto", Collections.singletonList(new PayloadButton("titulo", "conteudo")))));
  }
}
