package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.FacebookUser;
import lombok.*;

import java.util.Map;

@With @RequiredArgsConstructor
@AllArgsConstructor
@ToString @Getter @Setter
public class MensagemInterna {
    private final FacebookUser usuario;
    private final Map<String, Object> contexto;
    private TipoMensagem tipo;
    private String conteudo;
}
