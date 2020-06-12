package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;
import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.SIM;

public class UsuarioGreetingMessageProvider implements BotMessageProvider<TipoUsuario> {
    private Map<TipoUsuario, Function<Contexto, BotMessage>> usuarioToProviderMap;

    public UsuarioGreetingMessageProvider() {
        Function<Contexto, BotMessage> clienteProvider = contexto -> {
            List<QuickReplyButton> buttons = Stream.of("Oficinas e Cursos", "Talentos", "Informação e apoio", "Consultoria", "Suporte Emocional")
                    .map(it -> new QuickReplyButton(it, it))
                    .collect(Collectors.toList());
            return new BotMessage(contexto).withMessages(
                    new QuickReplyComponentBotMessage(
                            "Você quer especificar a area em que trabalha?",
                            buttons
                    )
            );
        };
        Function<Contexto, BotMessage> solucionadorProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new QuickReplyComponentBotMessage("Gostaria de especificar mais sua àrea de atuação?",
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
