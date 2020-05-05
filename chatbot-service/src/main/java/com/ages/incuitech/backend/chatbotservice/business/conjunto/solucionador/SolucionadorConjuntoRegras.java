package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;

import java.util.Arrays;
import java.util.Collections;

public class SolucionadorConjuntoRegras extends ConjuntoRegra {
    public SolucionadorConjuntoRegras() {
        super(Collections.emptyList());
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.SOLUCIONADOR.equals(tipoUsuario);
    }
}
