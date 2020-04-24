package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.RegraDoBot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EscolhaContatoRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return  message.getContexto().containsKey("jaFezSaudacao") &&
                message.getContexto().get("jaFezSaudacao").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message){
        return null;
    }
}




