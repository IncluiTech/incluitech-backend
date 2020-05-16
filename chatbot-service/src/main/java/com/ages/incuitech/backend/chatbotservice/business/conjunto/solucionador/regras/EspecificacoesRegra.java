package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;

import java.util.*;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.*;
import static java.util.stream.Collectors.*;

public class EspecificacoesRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("aguardandoEspecificacaoDeArea")
                && message.getContexto().get("aguardandoEspecificacaoDeArea").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        if (this.isPrimeiraInteracao(message.getConteudo())) {
            boolean confirmarTags = message.getConteudo().equals(NAO.getTexto());
            return confirmarTags ? this.confirmarTags(message) : this.perguntarSobreAreaDeAtuacao(message);
        }

        String instituicao = message.getConteudo();
        if (instituicao.equals("Finalizar Tags")) {
            return this.confirmarTags(message);
        }

        List<String> tags = this.getTagsFromContexto(message);
        tags.add(instituicao);
        message.getContexto().put("areasAtuacao", tags);
        return this.perguntarSobreAreaDeAtuacao(message);

    }

    private BotMessage confirmarTags(MensagemInterna message) {
        List<String> tags = this.getTagsFromContexto(message);
        List<String> instituicoes = this.getInstituicoesFromContexto(message);
        tags.addAll(instituicoes);
        String textTags = String.join(", ", tags);

        this.atualizarContexto(message);

        String mensagem = tags.isEmpty()
                ? "Você não especificou nenhuma especialização em sua área de atuação. Isto está correto?"
                : "Só confirmando, suas tags são: " + textTags;

        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage(mensagem,
                        new QuickReplyButton("É isso aí!", SIM.getTexto()),
                        new QuickReplyButton("Não", NAO.getTexto())
                )
        );
    }

    private void atualizarContexto(MensagemInterna message) {
        message.getContexto().remove("aguardandoEspecificacaoDeArea");
        message.getContexto().put("aguardandoConfirmacaoTags", true);
    }

    private List<String> getInstituicoesFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("instituicoes", new ArrayList<String>());
    }

    private List<String> getTagsFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("areasAtuacao", new ArrayList<String>());
    }

    private BotMessage perguntarSobreAreaDeAtuacao(MensagemInterna message) {
        List<String> tags = this.getTagsFromContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("TDAH", "Crianças", "Finalizar Tags"));
        defaults.removeAll(tags);
        List<QuickReplyButton> buttons = defaults.stream().map(tag -> new QuickReplyButton(tag, tag)).collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Por favor, selecione uma especificação abaixo", buttons)
        );
    }

    private boolean isPrimeiraInteracao(String payload) {
        return payload.equals(SIM.getTexto()) || payload.equals(NAO.getTexto());
    }
}
