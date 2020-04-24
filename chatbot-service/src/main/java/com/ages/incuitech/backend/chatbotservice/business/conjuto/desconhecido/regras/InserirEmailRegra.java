package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.FacebookMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.MessageType;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.AttachmentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.RegraDoBot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InserirEmailRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return false;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Map<String, Object> escolhaEmail = new HashMap<>();
        escolhaEmail.put("escolhaContatoFeita", true);
        return new BotMessage(escolhaEmail).withMessages(
                Arrays.asList(
                        new TextComponentBotMessage("Por favor, insira seu email: "),
                        new TextComponentBotMessage("Work")
                )
        );

    }
}
