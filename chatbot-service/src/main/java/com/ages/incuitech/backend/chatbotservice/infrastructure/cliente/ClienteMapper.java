package com.ages.incuitech.backend.chatbotservice.infrastructure.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {
    private ClienteMapper() {

    }

    public static ClienteRequest criarRequestDoClienteAPartirDeContexto(Contexto contexto, String facebookId) {
        String nome = contexto.getOrDefault("nome", "") + " " + contexto.getOrDefault("sobrenome", "");
        return ClienteRequest.builder()
                .nome(nome)
                .telefone((String) contexto.get(TipoContato.TELEFONE.getPropriedade()))
                .email((String) contexto.get(TipoContato.EMAIL.getPropriedade()))
                .funcao((String) contexto.get("funcao"))
                .tags(contexto.getOrDefault("tags", new ArrayList<>()))
                .instituicao((String) contexto.get("instituicao"))
                .facebookId(facebookId)
                .build();
    }
}
