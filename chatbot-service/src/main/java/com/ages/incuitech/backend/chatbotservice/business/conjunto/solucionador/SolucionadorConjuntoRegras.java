package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.ConfirmarTagsRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.EspecificacoesRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.LattesRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.SolucionadorInicialRegra;
import com.ages.incuitech.backend.chatbotservice.business.provider.UsuarioGreetingMessageProvider;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

import java.util.Arrays;

public class SolucionadorConjuntoRegras extends ConjuntoRegra {
    public SolucionadorConjuntoRegras(SolucaoDeProblemasClient client) {
        super(Arrays.asList(
                new LattesRegra(client),
                new EspecificacoesRegra(),
                new ConfirmarTagsRegra(new UsuarioGreetingMessageProvider()),
                new SolucionadorInicialRegra(),
                new CadastroSucessoSolucionador()
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.SOLUCIONADOR.equals(tipoUsuario);
    }
}
