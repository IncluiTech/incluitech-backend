package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.ContatoInformadoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.EscolhaContatoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.IniciarConversaRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.TipoUsuarioInformadoRegra;
import com.ages.incuitech.backend.chatbotservice.business.provider.ContatoMessageProvider;
import com.ages.incuitech.backend.chatbotservice.business.provider.UsuarioGreetingMessageProvider;
import com.ages.incuitech.backend.chatbotservice.business.service.FacebookService;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Arrays;

public class DesconhecidoConjuntoRegras extends ConjuntoRegra {

    public DesconhecidoConjuntoRegras(SolucaoDeProblemasClient client, FacebookService service) {
        super(Arrays.asList(
                new TipoUsuarioInformadoRegra(client, new UsuarioGreetingMessageProvider()),
                new ContatoInformadoRegra(),
                new EscolhaContatoRegra(new ContatoMessageProvider()),
                new IniciarConversaRegra(service)
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return tipoUsuario == null;
    }
}
