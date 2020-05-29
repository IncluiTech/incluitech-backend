package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.provider.BotMessageProvider;

import java.util.ArrayList;

public class ConfirmarTagsRegra implements RegraDoBot {

    private final BotMessageProvider<TipoUsuario> provider;

    public ConfirmarTagsRegra(BotMessageProvider<TipoUsuario> provider) {
        this.provider = provider;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoConfirmacaoTags", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().remove("aguardandoConfirmacaoTags");
        message.getContexto().put("primeiraMensagemUsuarioComTipo", true);
        message.getContexto().put("instituicoes", new ArrayList<String>());
        message.getContexto().put("areasAtuacao", new ArrayList<String>());
        BotMessage botMessage = provider.provide(TipoUsuario.CLIENTE, message.getContexto());
        botMessage.getMessages().add(0, new TextComponentBotMessage("Então, por favor, repita a seleção de tags."));
        return botMessage;
    }
}
