package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import lombok.*;

@RequiredArgsConstructor
@Getter @Setter
@ToString
public class UsuarioDaMensagem {
    private final String id;
    private TipoUsuario tipoUsuario;
}
