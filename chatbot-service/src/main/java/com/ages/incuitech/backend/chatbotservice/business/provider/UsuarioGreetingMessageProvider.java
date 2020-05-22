package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.*;

import java.util.*;
import java.util.function.*;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.*;

public class UsuarioGreetingMessageProvider implements BotMessageProvider<TipoUsuario> {
    private Map<TipoUsuario, Function<Contexto, BotMessage>> usuarioToProviderMap;

    public UsuarioGreetingMessageProvider() {
        Function<Contexto, BotMessage> clienteProvider = contexto -> new BotMessage(contexto).withMessages(
                new QuickReplyComponentBotMessage("Você está vinculado a algum tipo de instituição?",
                        new QuickReplyButton("Sim", SIM.name()),
                        new QuickReplyButton("Não", NAO.name())
                )
        );
        Function<Contexto, BotMessage> solucionadorProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new QuickReplyComponentBotMessage("Você está vinculado a algum tipo de instituição?",
                                new QuickReplyButton("Sim", SIM.name()),
                                new QuickReplyButton("Não", NAO.name())
                        )
                );

        this.usuarioToProviderMap = Map.of(
                TipoUsuario.CLIENTE, clienteProvider,
                TipoUsuario.SOLUCIONADOR, solucionadorProvider
        );
    }

    @Override
    public BotMessage provide(TipoUsuario tipoUsuario, Contexto contexto) {
        return this.usuarioToProviderMap.get(tipoUsuario).apply(contexto);
    }
}
