package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.domain.*;

import java.util.*;

public class ContatoInformadoRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoContato", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Contexto contexto = message.getContexto();
        TipoContato tipoContato = (TipoContato) contexto.get("tipoContato");
        String contato = message.getConteudo();

        if (tipoContato.equals(TipoContato.EMAIL_E_TELEFONE)) {
            return handleEmailETelefone(message, contexto, contato);
        }

        this.setPropertyInContext(contexto, tipoContato.getPropriedade(), contato);
        return this.seguirParProximoPasso(contexto);

    }

    private BotMessage handleEmailETelefone(MensagemInterna message, Contexto contexto, String contato) {
        boolean isEmailDefined = Objects.nonNull(message.getContexto().get(TipoContato.EMAIL.getPropriedade()));
        boolean isTelefoneDefined = Objects.nonNull(message.getContexto().get(TipoContato.TELEFONE.getPropriedade()));

        if (!isEmailDefined && !isTelefoneDefined) {
            this.setPropertyInContext(contexto, TipoContato.EMAIL.getPropriedade(), contato);
            return this.pedirTelefoneParaUsuario(contexto);
        }

        if (isEmailDefined) {
            this.setPropertyInContext(contexto, TipoContato.TELEFONE.getPropriedade(), contato);
            return this.seguirParProximoPasso(contexto);
        }

        return null;
    }

    private BotMessage pedirTelefoneParaUsuario(Contexto contexto) {
        contexto.put("aguardandoContato", true);
        return new BotMessage(contexto).withMessages(
                new TextComponentBotMessage("Agora, insira seu telefone:")
        );
    }

    private void setPropertyInContext(Contexto contexto, String property, String value) {
        contexto.put(property, value);
    }

    private BotMessage seguirParProximoPasso(Contexto contexto) {
        contexto.remove("aguardandoContato");
        contexto.remove("aguardandoTipoUsuario");
        contexto.put("aguardandoTipoUsuario", true);
        return new BotMessage(contexto).withMessages(
                new QuickReplyComponentBotMessage("Antes de seguirmos para o seu perfil, me diga, o que você procura?",
                        new QuickReplyButton("Busco soluções", TipoUsuario.CLIENTE.getTipo()),
                        new QuickReplyButton("Busco resolver problemas", TipoUsuario.SOLUCIONADOR.getTipo())
                )
        );
    }
}
