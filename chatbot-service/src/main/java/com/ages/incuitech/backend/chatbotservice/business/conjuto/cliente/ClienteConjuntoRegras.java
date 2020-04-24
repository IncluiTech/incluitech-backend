package com.ages.incuitech.backend.chatbotservice.business.conjuto.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.ConjuntoRegra;

import java.util.Arrays;


public class ClienteConjuntoRegras extends ConjuntoRegra  {
    public ClienteConjuntoRegras() {
        super(Arrays.asList());
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return TipoUsuario.CLIENTE.equals(tipoUsuario);
    }


}
