package com.ages.incuitech.backend.chatbotservice.business.conjunto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoContato {
    EMAIL("email"),
    TELEFONE("telefone"),
    EMAIL_E_TELEFONE("");
    private String propriedade;

    public static TipoContato getFromTexto(String texto) {
        return Arrays.stream(TipoContato.values())
                .filter(contato -> contato.propriedade.equals(texto))
                .findAny()
                .orElse(null);
    }
}
