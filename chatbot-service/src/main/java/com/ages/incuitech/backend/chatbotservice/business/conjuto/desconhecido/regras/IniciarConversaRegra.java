package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.RegraDoBot;

import java.util.Arrays;
import java.util.HashMap;

public class IniciarConversaRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return new BotMessage(new HashMap<>()).withMessages(
                Arrays.asList(new TextComponentBotMessage("Olá, Eu Sou a Helena"))
        );
    }
}
