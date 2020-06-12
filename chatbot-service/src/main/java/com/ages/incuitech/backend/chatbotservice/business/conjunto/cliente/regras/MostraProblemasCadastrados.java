package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.CarrouselComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.QuickReplyComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.PayloadButton;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemaDoCliente;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MostraProblemasCadastrados implements RegraDoBot {

    private static final Integer CARROUSEL_MAX_INDEX_SIZE = 8;
    private static final String MOSTRAR_MAIS_PROBLEMAS = "MOSTRAR_MAIS_PROBLEMAS";
    private List<String> payloadEntry = List.of(ProblemasCliente.PROBLEMAS_EXISTENTES.getCaminho(), MOSTRAR_MAIS_PROBLEMAS);
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
        if (message.getConteudo().equals(MOSTRAR_MAIS_PROBLEMAS)) {
            List<ProblemaDoCliente> problemasClientes = (List<ProblemaDoCliente>) message.getContexto().get("problemasCliente");
            return montaProblemasPaginado(message, problemasClientes);
        } else {
            List<ProblemaDoCliente> problemasClientes = pegaOsProblemasDoCliente(message.getUsuario().getId());
            if (problemasClientes.isEmpty()) return problemasVazio();
            message.getContexto().put("problemasCliente", problemasClientes);
            return mostraProblemas(message, problemasClientes);
        }
    }

    private BotMessage mostraProblemas(MensagemInterna message, List<ProblemaDoCliente> problemasClientes) {
        if(problemasClientes.size() > 10) {
            return montaProblemasPaginado(message, problemasClientes);
        } else {
            return montaProblemaMessage(message, problemasClientes);
        }
    }

    private BotMessage montaProblemasPaginado(MensagemInterna message, List<ProblemaDoCliente> problemasClientes) {
        Contexto contexto = message.getContexto();
        int ultimoIndex = contexto.getOrDefault("problemaIndex", 0);
        List<ProblemaDoCliente> problemasParaMostrar = problemasClientes.subList(ultimoIndex, ultimoIndex + CARROUSEL_MAX_INDEX_SIZE);
        contexto.put("problemaIndex", ultimoIndex + CARROUSEL_MAX_INDEX_SIZE);
        List<Card> cards = problemasParaMostrar.stream().map(this::buildCard).collect(Collectors.toList());
        Card lastCard = Card.builder().title("Ver mais problemas")
                .buttons(Collections.singletonList(new PayloadButton("Ver mais", MOSTRAR_MAIS_PROBLEMAS)))
                .build();
        cards.add(lastCard);
        return new BotMessage(contexto).withMessages(
                new CarrouselComponentBotMessage(cards)
        );
    }

    private BotMessage montaProblemaMessage(MensagemInterna message, List<ProblemaDoCliente> problemasClientes) {
        List<Card> cards = problemasClientes.stream()
                .map(this::buildCard)
                .collect(Collectors.toList());
        return new BotMessage(message.getContexto()).withMessages(new CarrouselComponentBotMessage(cards));
    }

    private Card buildCard(ProblemaDoCliente problema) {
        return Card.builder()
                .title(problema.getTitulo())
                .buttons(Collections.singletonList(new PayloadButton("Ver problema", "VER_PROBLEMA:" + problema.getId())))
                .build();
    }

    private BotMessage problemasVazio() {
        return new BotMessage(new Contexto()).withMessages(
                new TextComponentBotMessage("Você não tem nenhum problema cadastrado"),
                new QuickReplyComponentBotMessage("Para cadastrar um problema, selecione abaixo:",
                        new QuickReplyButton("Cadastrar Problema", ProblemasCliente.CADASTRAR_PROBLEMAS.getCaminho())
                )
        );
    }

    private List<ProblemaDoCliente> pegaOsProblemasDoCliente(String clientFacebookId) {
        return solucaoDeProblemasClient.getProblemasOfClient(clientFacebookId);
    }
}
