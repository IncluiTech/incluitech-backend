package com.ages.incuitech.backend.chatbotservice.business.service.contexto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;

import java.util.HashMap;
import java.util.Map;

public class MemoryContextManager implements ContextManager {

    private Map<String, Map<String, Object>> inMemoryContext;

    public MemoryContextManager() {
        this.inMemoryContext = new HashMap<>();
    }

    @Override
    public Map<String, Object> getContexto(UsuarioDaMensagem usuarioDaMensagem) {
        Map<String, Object> contexto = inMemoryContext.get(usuarioDaMensagem.getId());
        return contexto == null
                ? new HashMap<>()
                : contexto;
    }

    @Override
    public void saveContexto(UsuarioDaMensagem usuarioDaMensagem, Map<String, Object> contexto) {
        inMemoryContext.put(usuarioDaMensagem.getId(), contexto);
    }
}
