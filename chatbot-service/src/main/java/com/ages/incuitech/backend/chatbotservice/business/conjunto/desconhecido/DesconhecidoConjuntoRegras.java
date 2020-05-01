package com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.provider.ContatoMessageProvider;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.EscolhaContatoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.regras.IniciarConversaRegra;

import java.util.Arrays;

public class DesconhecidoConjuntoRegras extends ConjuntoRegra {
    public DesconhecidoConjuntoRegras() {
        super(Arrays.asList(new EscolhaContatoRegra(new ContatoMessageProvider()), new IniciarConversaRegra()));
    }

    @Override
    public boolean seleciona(TipoUsuario tipoUsuario) {
        return true;
    }
}
