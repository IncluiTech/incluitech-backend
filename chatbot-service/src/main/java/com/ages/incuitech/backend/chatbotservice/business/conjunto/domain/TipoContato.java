package com.ages.incuitech.backend.chatbotservice.business.conjunto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TipoContato {
    EMAIL("Email"),
    TELEFONE("Telefone/Whatsapp"),
    EMAIL_E_TELEFONE("Email e Telefone");
    private String texto;

    public static TipoContato getFromTexto(String texto) {
        return Arrays.stream(TipoContato.values())
                .filter(contato -> contato.texto.equals(texto))
                .findAny()
                .orElse(null);
    }
}
