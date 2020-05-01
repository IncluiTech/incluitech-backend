package com.ages.incluitech.backend.chatbotservice.business.service.conjutos;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;

import java.util.Collections;

public class TestDefaultConjuntoRegras extends ConjuntoRegra {
    public TestDefaultConjuntoRegras() {
        super(Collections.emptyList());
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return true;
    }
}
