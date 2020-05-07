package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.FacebookProfile;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;
import com.ages.incuitech.backend.chatbotservice.business.service.FacebookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> contexto = new HashMap<>();

        this.buscarNomeDoUsuarioESalvarNoContexto(contexto, message.getUsuario().getId());

        contexto.put("aguardandoDefinicaoContato", true);
        return new BotMessage(contexto).withMessages(
                new TextComponentBotMessage("Olá " + contexto.get("primeiroNome") + ", Eu Sou a Helena! Consultora e especialista da Incluitec."),
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

    private void buscarNomeDoUsuarioESalvarNoContexto(Map<String, Object> contexto, String id) {
        FacebookProfile perfil = this.facebookService.getProfile(id);
        contexto.put("nome", perfil.getFirstName());
        contexto.put("sobrenome", perfil.getLastName());
    }
}
