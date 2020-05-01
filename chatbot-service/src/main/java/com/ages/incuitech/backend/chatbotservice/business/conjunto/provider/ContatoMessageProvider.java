package com.ages.incuitech.backend.chatbotservice.business.conjunto.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.domain.TipoContato;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class ContatoMessageProvider implements BotMessageProvider<TipoContato> {
    private Map<TipoContato, Function<Map<String, Object>, BotMessage>> contatoToProviderMap;


    public ContatoMessageProvider() {
        Function<Map<String, Object>, BotMessage> emailProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        Collections.singletonList(
                                new TextComponentBotMessage("Por favor, insira seu email: ")
                        )
                );

        Function<Map<String, Object>, BotMessage> telefoneProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        Collections.singletonList(
                                new TextComponentBotMessage("Por favor, insira seu telefone: ")
                        )
                );

        this.contatoToProviderMap = Map.of(
                TipoContato.EMAIL, emailProvider,
                TipoContato.TELEFONE, telefoneProvider
        );
    }

    public BotMessage provide(TipoContato contato, Map<String, Object> contexto) {
        return this.contatoToProviderMap.get(contato).apply(contexto);
    }

}
