package com.ages.incuitech.backend.chatbotservice.business.service.contexto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;

public interface ContextManager {
    Contexto getContexto(UsuarioDaMensagem usuarioDaMensagem);
    void saveContexto(UsuarioDaMensagem usuarioDaMensagem, Contexto contexto);
}
