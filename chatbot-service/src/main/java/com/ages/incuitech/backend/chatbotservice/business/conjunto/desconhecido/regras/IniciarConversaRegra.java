package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.domain.*;
import com.ages.incuitech.backend.chatbotservice.business.service.*;

import java.util.*;

public class IniciarConversaRegra implements RegraDoBot {

    private FacebookService facebookService;

    public IniciarConversaRegra(FacebookService facebookService) {
        this.facebookService = facebookService;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        Map<String, Object> nomeESobrenome = this.buscarNomeDoUsuario(message.getUsuario().getId());
        Contexto contexto = new Contexto();
        contexto.putAll(nomeESobrenome);
        contexto.put("aguardandoDefinicaoContato", true);
        return new BotMessage(contexto).withMessages(
                new TextComponentBotMessage("Olá " + contexto.get("nome") + ", Eu Sou a Helena! Consultora e especialista da Incluitec."),
                new TextComponentBotMessage("Vi que é sua primeira vez por aqui, então antes de começarmos, " +
                        "preciso de algumas informações suas para entrar em contato."),
                new TextComponentBotMessage("Para isso preciso que responda algumas perguntas: "),
                new QuickReplyComponentBotMessage("Como prefere que eu entre em contato com você?",
                        new QuickReplyButton("Email", TipoContato.EMAIL.getPropriedade()),
                        new QuickReplyButton("Telefone/Whatsapp", TipoContato.TELEFONE.getPropriedade()),
                        new QuickReplyButton("Email e Telefone", TipoContato.EMAIL_E_TELEFONE.getPropriedade())
                )
        );
    }

    private Map<String, Object> buscarNomeDoUsuario(String id) {
        FacebookProfile perfil = this.facebookService.getProfile(id);
        return Map.of("nome", perfil.getFirstName(), "sobrenome", perfil.getLastName());
    }
}
