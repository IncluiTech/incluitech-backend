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
        return message.getContexto().propertyIsEqualsTo("aguardandoEspecificacaoDeArea", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        if (this.isPrimeiraInteracao(message.getConteudo())) {
            boolean confirmarTags = message.getConteudo().equals(NAO.name());
            return confirmarTags ? this.confirmarTags(message) : this.perguntarSobreAreaDeAtuacao(message);
        }

        String areaAtuacao = message.getConteudo();
        if (areaAtuacao.equals("Finalizar Tags")) {
            return this.confirmarTags(message);
        }

        List<String> tags = this.getTagsFromContexto(message);
        tags.add(areaAtuacao);
        message.getContexto().put("areasAtuacao", tags);
        return this.perguntarSobreAreaDeAtuacao(message);

    }

    private BotMessage confirmarTags(MensagemInterna message) {
        this.atualizarContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("Finalizar Tags","Comunidade", "Professores", "Alunos" ));
        List<QuickReplyButton> buttons = defaults.stream()
                .map(tag -> new QuickReplyButton(tag, tag))
                .collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Perfeito! Agora vamos espeficiar o seu Público Alvo!"),
                new QuickReplyComponentBotMessage("Por favor, selecione um público alvo abaixo", buttons)
        );
    }

    private void atualizarContexto(MensagemInterna message) {
        message.getContexto().remove("aguardandoEspecificacaoDeArea");
        message.getContexto().put("aguardandoEspecificacaoPublicoAlvo", true);
    }

    private List<String> getInstituicoesFromContexto(MensagemInterna message) {
        return message.getContexto().getOrDefault("instituicoes", new ArrayList<String>());
    }

    private List<String> getTagsFromContexto(MensagemInterna message) {
        return message.getContexto().getOrDefault("areasAtuacao", new ArrayList<String>());
    }

    private BotMessage perguntarSobreAreaDeAtuacao(MensagemInterna message) {
        List<String> tags = this.getTagsFromContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("Finalizar Tags","Oficinas e Cursos", "Talentos", "Informação e apoio", "Consultoria", "Suporte Emocional" ));
        defaults.removeAll(tags);
        List<QuickReplyButton> buttons = defaults.stream()
                .map(tag -> new QuickReplyButton(tag, tag))
                .collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Por favor, selecione uma especificação abaixo", buttons)
        );
    }

    private boolean isPrimeiraInteracao(String payload) {
        return payload.equals(SIM.name()) || payload.equals(NAO.name());
    }
}
