package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.FacebookProfile;
import com.ages.incuitech.backend.chatbotservice.business.provider.BotMessageProvider;
import com.ages.incuitech.backend.chatbotservice.business.service.FacebookService;

import java.util.Map;

public class RegraInicial implements RegraDoBot {

    private FacebookService facebookService;
    private BotMessageProvider<TipoUsuario> provider;

    public RegraInicial(FacebookService facebookService, BotMessageProvider<TipoUsuario> provider) {
        this.facebookService = facebookService;
        this.provider = provider;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Map<String, Object> profile = buscarNomeDoUsuario(message.getUsuario().getId());
        message.getContexto().putAll(profile);
        return provider.provide(message.getUsuario().getTipoUsuario(), message.getContexto());
    }

    private Map<String, Object> buscarNomeDoUsuario(String id) {
        FacebookProfile perfil = this.facebookService.getProfile(id);
        return Map.of("nome", perfil.getFirstName(), "sobrenome", perfil.getLastName());
    }
}
