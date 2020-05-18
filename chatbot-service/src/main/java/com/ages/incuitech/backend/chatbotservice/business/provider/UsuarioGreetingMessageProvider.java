package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;

import java.util.Map;
import java.util.function.Function;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;
import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.SIM;

public class UsuarioGreetingMessageProvider implements BotMessageProvider<TipoUsuario> {
    private Map<TipoUsuario, Function<Map<String, Object>, BotMessage>> usuarioToProviderMap;

    public UsuarioGreetingMessageProvider() {
        Function<Map<String, Object>, BotMessage> clienteProvider = contexto -> null;
        Function<Map<String, Object>, BotMessage> solucionadorProvider = contexto ->
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
    public BotMessage provide(TipoUsuario tipoUsuario, Map<String, Object> contexto) {
        return this.usuarioToProviderMap.get(tipoUsuario).apply(contexto);
    }
}
