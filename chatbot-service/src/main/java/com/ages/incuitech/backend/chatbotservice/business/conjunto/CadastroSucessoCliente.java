package com.ages.incuitech.backend.chatbotservice.business.conjunto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;

public class CadastroSucessoCliente implements RegraDoBot {
    @Override
    public BotMessage processa(MensagemInterna message) {
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Olá, trago novidades! Seu cadastro foi aprovado pelos administradores" +
                        "da IncluiTec e agora você já pode começar a usar a plataforma")
        );
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("notificarCadastroSucesso", true);
    }
}
