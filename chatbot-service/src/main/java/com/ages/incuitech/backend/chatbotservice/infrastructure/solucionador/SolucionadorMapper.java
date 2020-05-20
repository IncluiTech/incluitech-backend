package com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.business.domain.*;

public class SolucionadorMapper {
    private SolucionadorMapper() {

    }

    public static SolucionadorRequest criarRequestAPartirDeContexto(Contexto contexto, String facebookId) {
        String nome = contexto.getOrDefault("nome", "") + " " + contexto.getOrDefault("sobrenome", "");
        return SolucionadorRequest.builder()
                .nome(nome)
                .lattes((String) contexto.get("lattes"))
                .telefone((String) contexto.get(TipoContato.TELEFONE.getPropriedade()))
                .email((String) contexto.get(TipoContato.EMAIL.getPropriedade()))
                .facebookId(facebookId)
                .build();
    }
}
