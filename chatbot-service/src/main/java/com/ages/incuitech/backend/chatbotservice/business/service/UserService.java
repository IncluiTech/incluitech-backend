package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import com.ages.incuitech.backend.chatbotservice.infrastructure.User.UserRequest;
import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorRequest;

import java.util.Optional;

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
