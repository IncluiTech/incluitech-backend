package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.provider.BotMessageProvider;

public class TipoUsuarioInformadoRegra implements RegraDoBot {

    private BotMessageProvider<TipoUsuario> provider;

    public TipoUsuarioInformadoRegra(BotMessageProvider<TipoUsuario> provider) {
        this.provider = provider;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().get("aguardandoTipoUsuario").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String payload = message.getConteudo();
        TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(payload);
        message.getUsuario().setTipoUsuario(tipoUsuario);
        message.getContexto().remove("aguardandoTipoUsuario");
        BotMessage botMessage = provider.provide(tipoUsuario, message.getContexto());
        botMessage.getMessages().add(0, new TextComponentBotMessage("Legal, agora me fale um pouco mais sobre você."));
        return botMessage;
    }
}
