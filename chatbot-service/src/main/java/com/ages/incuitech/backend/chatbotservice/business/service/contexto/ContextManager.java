package com.ages.incuitech.backend.chatbotservice.business.service.contexto;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;

import java.util.Map;

public interface ContextManager {
    Context getContexto(UsuarioDaMensagem usuarioDaMensagem);
    void saveContexto(UsuarioDaMensagem usuarioDaMensagem, Context contexto);
}
