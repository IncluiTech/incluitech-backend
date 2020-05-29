package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras.ConfirmarTagsRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.regras.PerguntasTagsRegra;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Arrays;


public class ClienteConjuntoRegras extends ConjuntoRegra {
    public ClienteConjuntoRegras(SolucaoDeProblemasClient solucaoDeProblemasClient) {
        super(Arrays.asList(
                new ConfirmarTagsRegra(solucaoDeProblemasClient),
                new PerguntasTagsRegra()
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }


}
