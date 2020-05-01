package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class TipoUsuarioInformadoRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("aguardandoTipoUsuario");
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String payload = message.getConteudo();
        TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(payload);
        message.getUsuario().setTipoUsuario(tipoUsuario);
        return null;
    }
}
