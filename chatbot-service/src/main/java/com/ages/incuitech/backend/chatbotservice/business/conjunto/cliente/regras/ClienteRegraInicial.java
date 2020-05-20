package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClienteRegraInicial implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {

        String instituicao = message.getConteudo();
        if (instituicao.equals("Finalizar Tags")) {
            return this.perguntarSobreProximoPasso(message);
    }
        return perguntaSobreLigacoes(message);
    }

    private BotMessage perguntaSobreLigacoes(MensagemInterna message){
        List<String> instituicoes = this.getInstituicoesFromContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("ONG", "Escola", "Empresa", "Pessoa Física", "Finalizar Tags"));
        defaults.removeAll(instituicoes);
        List<QuickReplyButton> button = defaults.stream().map(instituicao -> new QuickReplyButton(instituicao, instituicao)).collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("A que tipo de instituição você está ligado?", (QuickReplyButton) button)
        );
    }

    private List<String> getInstituicoesFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("instituicoes", new ArrayList<String>());
    }

    private BotMessage perguntarSobreProximoPasso(MensagemInterna message){
        message.getContexto().put("aguardandoProximoPasso", true);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Agora, em que posso ajudar?",
                        new QuickReplyButton("Cadastrar um problema", ProblemasCliente.CADASTRAR_PROBLEMAS.getCaminho()),
                        new QuickReplyButton("Verificar problemas cadastrados", ProblemasCliente.PROBLEMAS_EXISTENTES
                                .getCaminho())
                )
        );
    }

}
