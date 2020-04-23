package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.RegraDoBot;

public class ComecoLoginRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getTipo().equals(TipoMensagem.BOTAO) && message.getContexto().containsKey("jaFezSaudacao") &&
                message.getContexto().get("jaFezSaudacao").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return null;
    }
}
