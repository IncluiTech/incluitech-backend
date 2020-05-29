package com.ages.incuitech.backend.chatbotservice.infrastructure.cliente;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.business.domain.*;

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
                .facebookId(facebookId)
                .build();
    }

    private static List<String> getTags(Contexto contexto) {
        List<String> instituicoes = (List<String>) contexto.getOrDefault("instituicoes", new ArrayList<>());
        List<String> areasAtuacao = (List<String>) contexto.getOrDefault("areasAtuacao", new ArrayList<>());
        List<String> tags = new ArrayList<>();
        tags.addAll(instituicoes);
        tags.addAll(areasAtuacao);
        return tags;
    }
}
