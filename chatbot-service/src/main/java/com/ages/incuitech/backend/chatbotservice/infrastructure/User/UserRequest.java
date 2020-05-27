package com.ages.incuitech.backend.chatbotservice.infrastructure.User;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;

public interface UserRequest {
    TipoUsuario getTipo();
}
