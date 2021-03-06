package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.TextComponentBotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.provider.BotMessageProvider;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import static com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorMapper.criarRequestAPartirDeContexto;
import static com.ages.incuitech.backend.chatbotservice.infrastructure.cliente.ClienteMapper.criarRequestDoClienteAPartirDeContexto;


public class TipoUsuarioInformadoRegra implements RegraDoBot {

    private SolucaoDeProblemasClient client;
    private BotMessageProvider<TipoUsuario> provider;


    public TipoUsuarioInformadoRegra(SolucaoDeProblemasClient client, BotMessageProvider<TipoUsuario> provider) {
        this.client = client;
        this.provider = provider;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().propertyIsEqualsTo("aguardandoTipoUsuario", true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String payload = message.getConteudo();
        TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(payload);
        this.salvarUsuario(message.getContexto(), message.getUsuario(), tipoUsuario);
        message.getUsuario().setTipoUsuario(tipoUsuario);
        message.getContexto().put("aguardandoEspecificacaoDeArea", true);
        message.getContexto().remove("aguardandoTipoUsuario");
        BotMessage botMessage = provider.provide(tipoUsuario, message.getContexto());
        botMessage.getMessages().add(0, new TextComponentBotMessage("Legal, agora me fale um pouco mais sobre você."));
        return botMessage;
    }

    private void salvarUsuario(Contexto contexto, UsuarioDaMensagem usuario, TipoUsuario tipoUsuario) {
        if (tipoUsuario == TipoUsuario.SOLUCIONADOR) {
            client.saveSolucionador(criarRequestAPartirDeContexto(contexto, usuario.getId()));
        }
        if (tipoUsuario == TipoUsuario.CLIENTE) {
            client.saveCliente(criarRequestDoClienteAPartirDeContexto(contexto, usuario.getId()));
        }
    }
}
