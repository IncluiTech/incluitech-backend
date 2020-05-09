package com.ages.incluitech.backend.chatbotservice.business.service.conjutos;

import com.ages.incluitech.backend.chatbotservice.business.service.conjutos.regras.ClienteContextoMockedRegra;
import com.ages.incluitech.backend.chatbotservice.business.service.conjutos.regras.ClientePerguntaMockedRegra;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;

import java.util.Arrays;

public class TestClienteMockedConjutoRegras extends ConjuntoRegra {
    public TestClienteMockedConjutoRegras() {
        super(Arrays.asList(new ClienteContextoMockedRegra(), new ClientePerguntaMockedRegra()));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }
}
