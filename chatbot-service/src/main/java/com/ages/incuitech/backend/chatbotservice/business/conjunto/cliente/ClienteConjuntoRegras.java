package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras.ConfirmarTagsRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras.PerguntasTagsRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras.RegraInicial;
import com.ages.incuitech.backend.chatbotservice.business.provider.GreetingMessageProvider;
import com.ages.incuitech.backend.chatbotservice.business.service.FacebookService;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Arrays;


public class ClienteConjuntoRegras extends ConjuntoRegra {
    public ClienteConjuntoRegras(SolucaoDeProblemasClient solucaoDeProblemasClient, FacebookService facebookService) {
        super(Arrays.asList(
                new ConfirmarTagsRegra(solucaoDeProblemasClient, new GreetingMessageProvider()),
                new PerguntasTagsRegra(),
                new RegraInicial(facebookService, new GreetingMessageProvider())
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }


}
