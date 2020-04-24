package com.ages.incuitech.backend.chatbotservice.business.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import org.springframework.web.client.RestTemplate;

public class UserService {
    private RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UsuarioDaMensagem getUsuario(String userId) {
        return new UsuarioDaMensagem(userId);
    }
}
