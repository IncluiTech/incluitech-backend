package com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras.EscolhaContatoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras.IniciarConversaRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.regras.InserirEmailRegra;

import java.util.Arrays;

public class DesconhecidoConjuntoRegras extends ConjuntoRegra {
    public DesconhecidoConjuntoRegras() {
        super(Arrays.asList(new InserirEmailRegra(), new EscolhaContatoRegra(),new IniciarConversaRegra()));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return true;
    }
}
