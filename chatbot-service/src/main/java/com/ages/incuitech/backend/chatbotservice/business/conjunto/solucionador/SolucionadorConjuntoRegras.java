package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.*;
import com.ages.incuitech.backend.chatbotservice.business.provider.UsuarioGreetingMessageProvider;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Arrays;

public class SolucionadorConjuntoRegras extends ConjuntoRegra {
    public SolucionadorConjuntoRegras(SolucaoDeProblemasClient client) {
        super(Arrays.asList(
                new LattesRegra(client),
                new ExperienciaRegra(),
                new PublicoAlvoRegra(),
                new EspecificacoesRegra(),
                new ConfirmarTagsRegra(new UsuarioGreetingMessageProvider()),
                new CadastroSucessoSolucionador()
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.SOLUCIONADOR.equals(tipoUsuario);
    }
}
