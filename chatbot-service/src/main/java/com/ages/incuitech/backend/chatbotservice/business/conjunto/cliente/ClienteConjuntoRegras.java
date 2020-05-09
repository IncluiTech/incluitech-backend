package com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;

import java.util.Collections;


public class ClienteConjuntoRegras extends ConjuntoRegra {
    public ClienteConjuntoRegras() {
        super(Collections.emptyList());
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }


}
