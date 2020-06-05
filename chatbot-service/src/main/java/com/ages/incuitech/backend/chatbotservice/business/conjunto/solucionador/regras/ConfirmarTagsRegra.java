package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.provider.*;

import java.util.*;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.*;

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
        if (message.getConteudo().equals(SIM.name())) {
            message.getContexto().put("aguardandoExperiencia", true);
            message.getContexto().remove("aguardandoConfirmacaoTags");
            return this.pedirExperiencia(message);
        }

        message.getContexto().remove("aguardandoConfirmacaoTags");
        message.getContexto().put("primeiraMensagemUsuarioComTipo", true);
        message.getContexto().put("instituicoes", new ArrayList<String>());
        message.getContexto().put("areasAtuacao", new ArrayList<String>());
        BotMessage botMessage = provider.provide(TipoUsuario.SOLUCIONADOR, message.getContexto());
        botMessage.getMessages().add(0, new TextComponentBotMessage("Então, por favor, repita a seleção de tags."));
        return botMessage;
    }

    private BotMessage pedirExperiencia(MensagemInterna message) {
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Fale um pouco sobre suas experiências (no máximo 1000 caracteres):")
        );
    }
}
