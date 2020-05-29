package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;
import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.SIM;
import static java.util.stream.Collectors.toList;

public class SolucionadorInicialRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("primeiraMensagemUsuarioComTipo", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        if (this.isPrimeiraInteracao(message.getConteudo())) {
            boolean vinculadoInstituicoes = message.getConteudo().equals(SIM.name());
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
        return conteudo.equals(SIM.name()) || conteudo.equals(NAO.name());
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
        return message.getContexto().getOrDefault("instituicoes", new ArrayList<>());
    }

    private BotMessage perguntarSobreAreaDeAtuacao(MensagemInterna message) {
        message.getContexto().put("aguardandoEspecificacaoDeArea", true);
        message.getContexto().remove("primeiraMensagemUsuarioComTipo");
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Gostaria de especificar mais sua àrea de atuação?",
                        new QuickReplyButton("Sim", SIM.name()),
                        new QuickReplyButton("Não", NAO.name())
                )
        );

    }
}
