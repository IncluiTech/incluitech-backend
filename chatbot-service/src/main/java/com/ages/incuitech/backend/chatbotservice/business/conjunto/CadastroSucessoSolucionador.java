package com.ages.incuitech.backend.chatbotservice.business.conjunto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;

public class CadastroSucessoSolucionador implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("notificarCadastroSucesso", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Olá, trago novidades! Seu cadastro foi aprovado pelos administradores" +
                        "da IncluiTec e agora você já pode começar a usar a plataforma")
        );
    }
}
