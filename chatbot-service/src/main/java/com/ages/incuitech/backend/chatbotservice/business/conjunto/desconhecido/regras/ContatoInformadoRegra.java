package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.domain.TipoContato;

public class ContatoInformadoRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("aguardandoContato")
                && message.getContexto().get("aguardandoContato").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().put("aguardandoContato", false);
        TipoContato tipoContato = (TipoContato) message.getContexto().get("tipoContato");
        if (tipoContato.equals(TipoContato.EMAIL_E_TELEFONE)) {
            return null;
        }

        String contato = message.getConteudo();
        message.getContexto().put(tipoContato.getPropriedade(), contato);
        message.getContexto().put("aguardandoTipoUsuario", true);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Antes de seguirmos para o seu perfil, me diga, o que você procura?",
                        new QuickReplyButton("Busco soluções", TipoUsuario.CLIENTE.getTipo()),
                        new QuickReplyButton("Busco resolver problemas", TipoUsuario.SOLUCIONADOR.getTipo())
                )
        );
    }
}
