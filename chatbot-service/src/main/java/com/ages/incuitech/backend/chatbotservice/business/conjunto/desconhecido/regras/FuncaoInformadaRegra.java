package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

public class FuncaoInformadaRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoFuncao", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Contexto contexto = message.getContexto();
        contexto.remove("aguardandoFuncao");
        contexto.put("funcao", message.getConteudo());
        contexto.put("aguardandoTipoUsuario", true);
        return new BotMessage(contexto).withMessages(
                new QuickReplyComponentBotMessage("Antes de seguirmos para o seu perfil, me diga, o que você procura?",
                        new QuickReplyButton("Preciso de ajuda", TipoUsuario.CLIENTE.getTipo()),
                        new QuickReplyButton("Quero ajudar", TipoUsuario.SOLUCIONADOR.getTipo())
                )
        );
    }
}
