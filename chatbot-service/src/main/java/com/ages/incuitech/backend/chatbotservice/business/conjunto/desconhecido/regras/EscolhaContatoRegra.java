package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;
import com.ages.incuitech.backend.chatbotservice.business.provider.BotMessageProvider;

import java.util.Objects;

public class EscolhaContatoRegra implements RegraDoBot {

    private BotMessageProvider<TipoContato> provider;

    public EscolhaContatoRegra(BotMessageProvider<TipoContato> provider) {
        this.provider = provider;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoDefinicaoContato", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().put("aguardandoDefinicaoContato", false);
        String payload = message.getConteudo();
        TipoContato tipoContato = TipoContato.getFromTexto(payload);
        if (Objects.nonNull(tipoContato)) {
            message.getContexto().put("tipoContato", tipoContato);
            message.getContexto().put("aguardandoContato", true);
            return provider.provide(tipoContato, message.getContexto());
        }

        return null;
    }
}




