package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class FuncaoInformadaRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoFuncao", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().remove("aguardandoFuncao");
        Contexto contexto = message.getContexto();
        contexto.put("aguardandoFuncao", true);
        return new BotMessage(contexto).withMessages(
                new TextComponentBotMessage("Informe a sua função nessa instituição:")
        );
    }
}
