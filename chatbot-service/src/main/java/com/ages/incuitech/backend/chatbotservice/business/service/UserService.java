package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;

public class UserService {
    private SolucaoDeProblemasClient client;

    public UserService(SolucaoDeProblemasClient client) {
        this.client = client;
    }

    public UsuarioDaMensagem getUsuario(String userId) {
         return this.client.getByFacebookId(userId)
                 .map(it -> new UsuarioDaMensagem(userId, it.getTipo()))
                 .orElse(new UsuarioDaMensagem(userId));
    }
}
