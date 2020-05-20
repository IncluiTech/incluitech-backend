package com.ages.incuitech.backend.chatbotservice.business.service.contexto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;

import java.util.HashMap;
import java.util.Map;

public class MemoryContextManager implements ContextManager {

    private Map<String, Context> inMemoryContext;

    public MemoryContextManager() {
        this.inMemoryContext = new HashMap<>();
    }

    @Override
    public Context getContexto(UsuarioDaMensagem usuarioDaMensagem) {
        Context contexto = inMemoryContext.get(usuarioDaMensagem.getId());
        return contexto == null
                ? new Context()
                : contexto;
    }

    @Override
    public void saveContexto(UsuarioDaMensagem usuarioDaMensagem, Context contexto) {
        inMemoryContext.put(usuarioDaMensagem.getId(), contexto);
    }
}
