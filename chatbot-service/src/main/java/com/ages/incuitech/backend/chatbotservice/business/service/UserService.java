package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.TipoUsuario;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorRequest;

public class UserService {
    private SolucaoDeProblemasClient client;

    public UserService(SolucaoDeProblemasClient client) {
        this.client = client;
    }

    public UsuarioDaMensagem getUsuario(String userId) {
        SolucionadorRequest request = this.client.getByFacebookId(userId);
        return request != null
                ? new UsuarioDaMensagem(request.getFacebookId(), TipoUsuario.CLIENTE)
                : new UsuarioDaMensagem(userId);
    }

}
