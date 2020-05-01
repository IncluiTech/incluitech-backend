package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.domain.TipoContato;

public class ContatoInformadoRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("tipoContato");
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        TipoContato tipoContato = (TipoContato) message.getContexto().get("tipoContato");
        if (tipoContato.equals(TipoContato.EMAIL_E_TELEFONE)) {
            return null;
        }

        String contato = message.getConteudo();
        message.getContexto().put(tipoContato.getPropriedade(), contato);
        message.getContexto().put("fluxoContatoConcluido", true);
        return null;
    }
}
