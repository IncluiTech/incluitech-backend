package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemasCliente;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

public class CadastraTituloProblemaRegra implements RegraDoBot {

    @Override
    public boolean verifica(MensagemInterna message) {
        return usuarioQuerCadastrarProblema(message) || usuarioVaiDigitarDescricao(message);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        return usuarioQuerCadastrarProblema(message)
                ? perguntaTitulo(message)
                : perguntaDescricao(message);
    }

    private BotMessage perguntaDescricao(MensagemInterna message) {
        message.getContexto().put("problemaTitulo", message.getConteudo());
        message.getContexto().put("preenchendoDescricao", true);
        message.getContexto().remove("cadastrandoProblema");
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Certo, agora, por favor, digite a descrição do seu problema")
        );
    }

    private BotMessage perguntaTitulo(MensagemInterna message) {
        message.getContexto().put("cadastrandoProblema", true);
        return new BotMessage(message.getContexto()).withMessages(
                new TextComponentBotMessage("Então vamo lá"),
                new TextComponentBotMessage("Dê um breve titulo para o seu problema")
        );
    }

    private boolean usuarioVaiDigitarDescricao(MensagemInterna message) {
        return message.getTipo().equals(TipoMensagem.TEXTO) &&
                message.getContexto().propertyIsEqualsTo("cadastrandoProblema", true);
    }

    private boolean usuarioQuerCadastrarProblema(MensagemInterna message) {
        return message.getTipo().equals(TipoMensagem.BOTAO) &&
                message.getConteudo().equals(ProblemasCliente.CADASTRAR_PROBLEMAS.getCaminho());
    }
}
