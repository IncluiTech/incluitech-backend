package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoUsuario {
    SOLUCIONADOR("solucionador"), CLIENTE("cliente");

    private String tipo;
}
