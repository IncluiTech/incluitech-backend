package com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.regras.SolucionadorInicialRegra;

import java.util.Arrays;

public class SolucionadorConjuntoRegras extends ConjuntoRegra {
    public SolucionadorConjuntoRegras() {
        super(Arrays.asList(
                new SolucionadorInicialRegra()
        ));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.SOLUCIONADOR.equals(tipoUsuario);
    }
}
