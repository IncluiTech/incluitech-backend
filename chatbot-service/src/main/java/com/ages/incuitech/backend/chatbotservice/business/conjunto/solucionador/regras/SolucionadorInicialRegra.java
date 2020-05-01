package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class SolucionadorInicialRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().put("aguardandoLigacaoComInstituicao", true);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Legal, agora me fale um pouco mais sobre você. Você está vinculado a algum tipo de instituição?",
                        new QuickReplyButton("Sim", "sim"),
                        new QuickReplyButton("Não", null)
                )
        );
    }
}
