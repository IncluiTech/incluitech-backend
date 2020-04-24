package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.RegraDoBot;

import java.util.*;

public class IniciarConversaRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return true;

    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Map<String, Object> contexto = new HashMap<>();
        contexto.put("jaFezSaudacao", true);
        return new BotMessage(contexto).withMessages(
                Arrays.asList(
                        new TextComponentBotMessage("Olá, Eu Sou a Helena! Consultora e especialista da Incluitec."),
                        new TextComponentBotMessage("Vi que é sua primeira vez por aqui, então antes de começarmos, " +
                                "preciso de algumas informações suas para entrar em contato."),
                        new TextComponentBotMessage("Para isso preciso que responda algumas perguntas: "),
                        new QuickReplyComponentBotMessage("Como prefere que eu entre em contato com você?",
                                Arrays.asList(
                                        new QuickReplyButton("Email","escolhaContato"),
                                        new QuickReplyButton("Telefone/Whatsapp", "escolhaContato"),
                                        new QuickReplyButton("Email e Telefone", "escolhaContato")
                                )
                        )
                )
        );
    }
}
