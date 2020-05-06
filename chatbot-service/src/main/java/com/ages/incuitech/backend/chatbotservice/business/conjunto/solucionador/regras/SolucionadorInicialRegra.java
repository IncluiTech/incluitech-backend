package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.SimNao;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class SolucionadorInicialRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return true;
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        if (this.isPrimeiraInteracao(message.getConteudo())) {
            boolean vinculadoInstituicoes = message.getConteudo().equals(SimNao.SIM.getTexto());
            return vinculadoInstituicoes ? this.perguntarSobreInstituicoes(message) : this.perguntarSobreAreaDeAtuacao(message);
        }

        String instituicao = message.getConteudo();
        if (instituicao.equals("Finalizar Tags")) {
            return this.perguntarSobreAreaDeAtuacao(message);
        }

        List<String> instituicoes = this.getInstituicoesFromContexto(message);
        instituicoes.add(instituicao);
        message.getContexto().put("instituicoes", instituicoes);
        return perguntarSobreInstituicoes(message);
    }

    private boolean isPrimeiraInteracao(String conteudo) {
        return conteudo.equals(SimNao.SIM.getTexto()) || conteudo.equals(SimNao.NAO.getTexto());
    }

    private BotMessage perguntarSobreInstituicoes(MensagemInterna message) {
        List<String> instituicoes = this.getInstituicoesFromContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("ONG", "Escola", "Empresa", "Pessoa Física", "Finalizar Tags"));
        defaults.removeAll(instituicoes);
        List<QuickReplyButton> buttons = defaults.stream().map(instituicao -> new QuickReplyButton(instituicao, instituicao)).collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("A que tipo de instituição você está ligado?", buttons)
        );
    }

    private List<String> getInstituicoesFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("instituicoes", new ArrayList<String>());
    }

    private BotMessage perguntarSobreAreaDeAtuacao(MensagemInterna message) {
        message.getContexto().put("aguardandoEspecificacaoDeArea", true);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Gostaria de especificar mais sua àrea de atuação?",
                        new QuickReplyButton("Sim", SimNao.SIM.getTexto()),
                        new QuickReplyButton("Não", SimNao.NAO.getTexto())
                )
        );

    }
}
