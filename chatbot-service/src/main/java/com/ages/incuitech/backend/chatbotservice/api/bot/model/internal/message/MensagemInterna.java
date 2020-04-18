package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;


@ToString @Getter @Setter
public class MensagemInterna {
    private final UsuarioDaMensagem usuario;
    private final TipoMensagem tipo;
    private final String conteudo;
    private Map<String, Object> contexto;

    public MensagemInterna(UsuarioDaMensagem usuario, TipoMensagem tipo, String conteudo) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.conteudo = conteudo;
    }
}
