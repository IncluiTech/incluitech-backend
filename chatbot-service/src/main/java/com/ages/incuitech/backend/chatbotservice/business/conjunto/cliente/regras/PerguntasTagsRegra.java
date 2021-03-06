package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;
import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.SIM;
import static java.util.stream.Collectors.toList;

public class PerguntasTagsRegra implements RegraDoBot {

    private static final String FINALIZAR_TAGS = "Finalizar Tags";
    private static final List<String> TAGS_INICIAIS = Arrays.asList("Oficinas e Cursos", "Talentos", "Informação e apoio", "Consultoria", "Suporte Emocional");

    @Override
    public boolean verifica(MensagemInterna message) {
        Contexto contexto = message.getContexto();
        return contexto.propertyIsEqualsTo("aguardandoEspecificacaoDeArea", true) ||
                contexto.propertyIsEqualsTo("clienteEstáPreenchendoTags", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        message.getContexto().removeIfExists("aguardandoEspecificacaoDeArea");
        message.getContexto().put("clienteEstáPreenchendoTags", true);
        if (message.getTipo() != TipoMensagem.BOTAO) return repeteTags(message);
        String tag = message.getConteudo();
        if (tag.equals(FINALIZAR_TAGS)) {
            return perguntaConfirmaTags(message);
        }
        return perguntaSobreLigacoes(message);
    }

    private BotMessage repeteTags(MensagemInterna message) {
        List<String> tags = message.getContexto().getOrDefault("tagsRestantes", TAGS_INICIAIS);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Não entendi, por favor selecione uma tag abaixo",buildTagsButtons(tags))
        );
    }

    private BotMessage perguntaSobreLigacoes(MensagemInterna message) {
        String tag = message.getConteudo();
        List<String> tagsRestantes = message.getContexto().getOrDefault("tagsRestantes", new ArrayList<>(TAGS_INICIAIS));
        List<String> tags = message.getContexto().getOrDefault("tags", new ArrayList<>());
        tags.add(tag);
        if(!tagsRestantes.contains(FINALIZAR_TAGS))tagsRestantes.add(0,FINALIZAR_TAGS);
        tagsRestantes.removeAll(tags);
        message.getContexto().put("tags", tags);
        message.getContexto().put("tagsRestantes", tagsRestantes);
        List<QuickReplyButton> buttons = buildTagsButtons(tagsRestantes);
        return new BotMessage(message.getContexto()).withMessages(
                new QuickReplyComponentBotMessage("Você adicionou " + tag + ", deseja adicionar mais alguma tag?",buttons)
        );
    }

    private List<QuickReplyButton> buildTagsButtons(List<String> tags) {
        return tags.stream()
                .map(tag -> new QuickReplyButton(tag, tag))
                .collect(toList());
    }

    private BotMessage perguntaConfirmaTags(MensagemInterna message) {
        message.getContexto().put("clienteConfirmandoTags", true);
        message.getContexto().remove("tagsRestantes");
        message.getContexto().remove("clienteEstáPreenchendoTags");
        String tags = String.join(", ", ((List<String>) message.getContexto().get("tags")));
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Confirma pra mim"),
                new TextComponentBotMessage("Essas aqui foram as tags que você selecionou:"),
                new QuickReplyComponentBotMessage(tags,
                        new QuickReplyButton("Sim", SIM.name()),
                        new QuickReplyButton("Nao", NAO.name())
                )
        );
    }
}
