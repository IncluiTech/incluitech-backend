package com.ages.incuitech.backend.chatbotservice.business.service.contexto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;

import java.util.Map;

public interface ContextManager {
    Map<String, Object> getContexto(UsuarioDaMensagem usuarioDaMensagem);
    void saveContexto(UsuarioDaMensagem usuarioDaMensagem, Map<String, Object> contexto);
}
