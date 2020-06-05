package com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Contexto;
import com.ages.incuitech.backend.chatbotservice.business.domain.TipoContato;

import java.util.ArrayList;
import java.util.List;

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
                .funcao((String) contexto.get("funcao"))
                .instituicao((String) contexto.get("instituicao"))
                .experiencia((String) contexto.get("experiencia"))
                .tags(getTags(contexto))
                .facebookId(facebookId)
                .build();
    }

    private static List<String> getTags(Contexto contexto) {
        List<String> instituicoes = contexto.getOrDefault("instituicoes", new ArrayList<>());
        List<String> areasAtuacao = contexto.getOrDefault("areasAtuacao", new ArrayList<>());
        List<String> tags = new ArrayList<>();
        tags.addAll(instituicoes);
        tags.addAll(areasAtuacao);
        return tags;
    }
}
