package com.ages.incuitech.backend.chatbotservice.business.provider;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;

import java.util.Map;
import java.util.function.Function;

public class ContatoMessageProvider implements BotMessageProvider<TipoContato> {
    private Map<TipoContato, Function<Context, BotMessage>> contatoToProviderMap;


    public ContatoMessageProvider() {
        Function<Context, BotMessage> emailProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new TextComponentBotMessage("Por favor, insira seu email: ")
                );

        Function<Context, BotMessage> telefoneProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new TextComponentBotMessage("Por favor, insira seu telefone: ")
                );

        Function<Context, BotMessage> emailETelefoneProvider = contexto ->
                new BotMessage(contexto).withMessages(
                        new TextComponentBotMessage("Primeiro, por favor, insira seu email: ")
                );

        this.contatoToProviderMap = Map.of(
                TipoContato.EMAIL, emailProvider,
                TipoContato.TELEFONE, telefoneProvider,
                TipoContato.EMAIL_E_TELEFONE, emailETelefoneProvider
        );
    }

    @Override
    public BotMessage provide(TipoContato contato, Context contexto) {
        return this.contatoToProviderMap.get(contato).apply(contexto);
    }

}
