package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;

public class ExperienciaRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoExperiencia", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().remove("aguardandoExperiencia");
        Contexto contexto = message.getContexto();
        contexto.put("experiencia", message.getConteudo());
        contexto.put("aguardandoLattes", true);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Agora preciso que insira o link de seu currículo Lattes:",
                        new QuickReplyButton("Não possuo", NAO.name())
                )
        );
    }

}
