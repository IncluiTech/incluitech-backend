package com.ages.incuitech.backend.chatbotservice.business.conjunto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class CadastroSucessoCliente implements RegraDoBot {
    @Override
    public BotMessage processa(MensagemInterna message) {
        return null;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return false;
    }
}
