package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.MensagemInterna;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.RegraDoBot;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorRequest;

import java.util.Map;


public class TipoUsuarioInformadoRegra implements RegraDoBot {

    private SolucaoDeProblemasClient client;

    public TipoUsuarioInformadoRegra(SolucaoDeProblemasClient client) {
        this.client = client;
    }

    @Override
    public boolean verifica(MensagemInterna message) {
        return message.getContexto().containsKey("aguardandoTipoUsuario")
                && message.getContexto().get("aguardandoTipoUsuario").equals(true);
    }

    @Override
    public BotMessage processa(MensagemInterna message) {
        String payload = message.getConteudo();
        TipoUsuario tipoUsuario = TipoUsuario.getFromTipo(payload);
        this.salvarUsuario(message.getContexto(), message.getUsuario(), tipoUsuario);
        message.getUsuario().setTipoUsuario(tipoUsuario);
        message.getContexto().put("aguardandoTipoUsuario", false);
        return null;
    }

    private void salvarUsuario(Map<String, Object> contexto, UsuarioDaMensagem usuario, TipoUsuario tipoUsuario) {
        if (tipoUsuario == TipoUsuario.SOLUCIONADOR) {
            SolucionadorRequest request = SolucionadorRequest.builder()
                    .nome("default")
                    .telefone((String) contexto.get(TipoContato.TELEFONE.getPropriedade()))
                    .email((String) contexto.get(TipoContato.EMAIL.getPropriedade()))
                    .facebookId(usuario.getId())
                    .build();
            client.saveSolucionador(request);
        }
    }
}
