package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemaDoCliente;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

public class CadastraProblemaRegra implements RegraDoBot {
    private SolucaoDeProblemasClient solucaoDeProblemasClient;

    public CadastraProblemaRegra(SolucaoDeProblemasClient solucaoDeProblemasClient) {
        this.solucaoDeProblemasClient = solucaoDeProblemasClient;
    }


    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("preenchendoDescricao", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String descricao = message.getConteudo();
        String titulo = (String) message.getContexto().get("problemaTitulo");
        salvaProblema(descricao, titulo, message.getUsuario());
        return new BotMessage(new Contexto()).withMessages(
                new TextComponentBotMessage("Ok, seu problema ja foi cadastrado"),
                new TextComponentBotMessage("Em breve entraremos em contato pelo seu meio de contato informado")
        );
    }

    private void salvaProblema(String descricao, String titulo, UsuarioDaMensagem usuario) {
        ProblemaDoCliente problemaDoCliente = ProblemaDoCliente.builder().titulo(titulo).descricao(descricao).build();
        solucaoDeProblemasClient.salvaProblemaCliente(problemaDoCliente, usuario);
    }
}
