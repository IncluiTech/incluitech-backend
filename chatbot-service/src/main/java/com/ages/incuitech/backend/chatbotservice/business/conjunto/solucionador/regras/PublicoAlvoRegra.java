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

public class PublicoAlvoRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoEspecificacaoPublicoAlvo", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String publicoAlvo = message.getConteudo();
        if (publicoAlvo.equals("Finalizar Tags") || publicoAlvo.equals("Prosseguir")) {
            return this.confirmarTags(message);
        }

        List<String> tags = this.getPublicosAlvoFromContexto(message);
        tags.add(publicoAlvo);
        message.getContexto().put("publicosAlvo", tags);
        return this.perguntarSobrePublicoAlvo(message);

    }

    private BotMessage confirmarTags(MensagemInterna message) {
        List<String> areasAtuacao = this.getTagsFromContexto(message);
        List<String> publicosAlvo = this.getPublicosAlvoFromContexto(message);
        List<String> tags = new ArrayList<>();
        tags.addAll(areasAtuacao);
        tags.addAll(publicosAlvo);
        String textTags = String.join(", ", tags);

        this.atualizarContexto(message);

        String mensagem = tags.isEmpty()
                ? "Você não especificou nenhuma especialização ou público alvo em sua área de atuação. Isto está correto?"
                : "Só confirmando, suas tags são: " + textTags;

        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage(mensagem,
                        new QuickReplyButton("É isso aí!", SIM.name()),
                        new QuickReplyButton("Não", NAO.name())
                )
        );
    }

    private void atualizarContexto(MensagemInterna message) {
        message.getContexto().remove("aguardandoEspecificacaoPublicoAlvo");
        message.getContexto().put("aguardandoConfirmacaoTags", true);
    }

    private List<String> getTagsFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("areasAtuacao", new ArrayList<String>());
    }

    private List<String> getPublicosAlvoFromContexto(MensagemInterna message) {
        return (List<String>) message.getContexto().getOrDefault("publicosAlvo", new ArrayList<String>());
    }

    private BotMessage perguntarSobrePublicoAlvo(MensagemInterna message) {
        List<String> tags = this.getPublicosAlvoFromContexto(message);
        List<String> defaults = new ArrayList<>(Arrays.asList("Prosseguir","Comunidade", "Professores", "Alunos"));
        defaults.removeAll(tags);
        List<QuickReplyButton> buttons = defaults.stream()
                .map(tag -> new QuickReplyButton(tag, tag))
                .collect(toList());
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Legal! Selecione mais algum público alvo ou passe para a próxima etapa:", buttons)
        );
    }
}
