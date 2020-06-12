package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.List;

public class MostraProblemasCadastrados implements RegraDoBot {

    private List<String> payloadEntry = List.of(ProblemasCliente.PROBLEMAS_EXISTENTES.getCaminho(), "MOSTRAR_MAIS_PROBLEMAS");
    private SolucaoDeProblemasClient solucaoDeProblemasClient;

    public MostraProblemasCadastrados(SolucaoDeProblemasClient solucaoDeProblemasClient) {
        this.solucaoDeProblemasClient = solucaoDeProblemasClient;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getTipo().equals(TipoMensagem.BOTAO) && payloadEntry.contains(message.getConteudo());
    }

    @Override
    public BotMessage processa(MensagemInterna message) {

    }
}
