package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;
import com.ages.incuitech.backend.chatbotservice.business.domain.SimNao;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import com.ages.incuitech.backend.chatbotservice.infrastructure.cliente.ClienteRequest;

import java.util.Arrays;
import java.util.List;

import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.NAO;
import static com.ages.incuitech.backend.chatbotservice.business.domain.SimNao.SIM;
import static com.ages.incuitech.backend.chatbotservice.infrastructure.cliente.ClienteMapper.criarRequestDoClienteAPartirDeContexto;

public class ConfirmarTagsRegra implements RegraDoBot {
    private static final List<String> confirmation = Arrays.asList(SIM.name().toLowerCase(), NAO.name().toLowerCase());
    private final SolucaoDeProblemasClient solucaoDeProblemasClient;

    public ConfirmarTagsRegra(SolucaoDeProblemasClient solucaoDeProblemasClient) {
        this.solucaoDeProblemasClient = solucaoDeProblemasClient;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("clienteConfirmandoTags", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return confirmation.contains(message.getConteudo().toLowerCase())
                ? validaConfirmacao(message)
                : perguntaConfirmcao(message);
    }

    private BotMessage validaConfirmacao(MensagemInterna message) {
        return SimNao.valueOf(message.getConteudo()) == SIM
                ? perguntaProximoPasso(message)
                : perguntaTags(message);
    }

    //@ToDo validar resposta pós cadastro.
    private BotMessage perguntaProximoPasso(MensagemInterna message) {
        updateCliente(message);
        return new BotMessage(new Contexto()).withMessages(
                new TextComponentBotMessage("Legal, em breve você recebará um email informando se seu cadastro foi " +
                        "aprovado ou não"),
                new QuickReplyComponentBotMessage("Agora, em que posso ajudar?",
                        new QuickReplyButton("Cadastrar um problema", ProblemasCliente.CADASTRAR_PROBLEMAS.getCaminho()),
                        new QuickReplyButton("Verificar problemas cadastrados", ProblemasCliente.PROBLEMAS_EXISTENTES
                                .getCaminho())
                )
        );
    }

    private void updateCliente(MensagemInterna message) {
        ClienteRequest request = criarRequestDoClienteAPartirDeContexto(message.getContexto(), message.getUsuario().getId());
        solucaoDeProblemasClient.updateCliente(request);
    }

    private BotMessage perguntaTags(MensagemInterna message) {
        message.getContexto().removeIfExists("tags");
        message.getContexto().removeIfExists("clienteConfirmandoTags");
        message.getContexto().put("clienteEstáPreenchendoTags", true);
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Por favor, selecione abaixo quais são as areas que você atua")
        );
    }

    private BotMessage perguntaConfirmcao(MensagemInterna message) {
        String tags = String.join(", ", (List<String>) message.getContexto().get("tags"));
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Por favor, confirme se essas são as tags selecionadas"),
                new QuickReplyComponentBotMessage(tags,
                        new QuickReplyButton("Sim", SIM.name()),
                        new QuickReplyButton("Não", NAO.name())
                )
        );
    }
}
