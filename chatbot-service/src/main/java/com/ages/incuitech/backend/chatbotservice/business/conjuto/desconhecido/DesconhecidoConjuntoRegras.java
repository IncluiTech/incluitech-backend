package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras.ComecoLoginRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras.IniciarConversaRegra;

import java.util.Arrays;

public class DesconhecidoConjuntoRegras extends ConjuntoRegra {
    public DesconhecidoConjuntoRegras() {
        super(Arrays.asList(new ComecoLoginRegra(), new IniciarConversaRegra()));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return true;
    }
}
