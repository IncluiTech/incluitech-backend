package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemaDoCliente;

import java.util.List;

public class MostraProblemaRegra implements RegraDoBot {
    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getTipo().equals(TipoMensagem.BOTAO) &&
                message.getConteudo().startsWith("VER_PROBLEMA:");
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        List<ProblemaDoCliente> problemas = (List<ProblemaDoCliente>) message.getContexto().get("problemasCliente");
        Long problemaId = Long.parseLong(message.getConteudo().split(":")[1]);
        return problemas.stream().filter(it -> it.getId().equals(problemaId))
                .findFirst()
                .map(it -> buildProblema(message, it))
                .orElseGet(() -> new BotMessage(message.getContexto()).withMessages(
                        new TextComponentBotMessage("Não consegui encontrar o problema que você selecionou"),
                        new TextComponentBotMessage("Por favor, tente outro problema"))
                );
    }

    private BotMessage buildProblema(MensagemInterna message, ProblemaDoCliente problemaDoCliente) {
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Ok, segue a descrição do problema que você selecionou"),
                new TextComponentBotMessage("Problema: " + problemaDoCliente.getTitulo()),
                new TextComponentBotMessage(problemaDoCliente.getDescricao())
        );
    }
}
