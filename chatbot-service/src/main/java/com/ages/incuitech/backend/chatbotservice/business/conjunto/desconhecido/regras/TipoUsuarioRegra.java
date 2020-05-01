package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class TipoUsuarioRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("fluxoContatoConcluido");
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("",
                        new QuickReplyButton("Busco soluções", TipoUsuario.CLIENTE.getTipo()),
                        new QuickReplyButton("Busco resolver problemas", TipoUsuario.SOLUCIONADOR.getTipo())
                )
        );
    }
}
