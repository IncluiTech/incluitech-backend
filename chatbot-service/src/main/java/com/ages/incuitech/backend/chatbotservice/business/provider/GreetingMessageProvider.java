package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.ButtonComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.PayloadButton;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;

import java.util.Map;
import java.util.function.Function;

public class GreetingMessageProvider implements BotMessageProvider<TipoUsuario> {

    Map<TipoUsuario, Function<Contexto, BotMessage>> providersMap;

    public GreetingMessageProvider() {
        Function<Contexto, BotMessage> clientGreetingProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new ButtonComponentBotMessage("Agora, em que posso ajudar?",
                                new PayloadButton("Cadastrar um problema", ProblemasCliente.CADASTRAR_PROBLEMAS.getCaminho()),
                                new PayloadButton("Problemas cadastrados", ProblemasCliente.PROBLEMAS_EXISTENTES
                                        .getCaminho())
                        )
                );
        this.providersMap = Map.of(TipoUsuario.CLIENTE, clientGreetingProvider);
    }

    @Override
    public BotMessage provide(TipoUsuario tipoUsuario, Contexto contexto) {
        return this.providersMap.get(tipoUsuario).apply(contexto);
    }
}
