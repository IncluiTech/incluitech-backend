package com.ages.incuitech.backend.chatbotservice;

import com.ages.incuitech.backend.chatbotservice.api.service.FacebookSendService;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.ClienteConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.DesconhecidoConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.SolucionadorConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.service.BotEngine;
import com.ages.incuitech.backend.chatbotservice.business.service.BotService;
import com.ages.incuitech.backend.chatbotservice.business.service.UserService;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.ContextManager;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.MemoryContextManager;
import com.ages.incuitech.backend.chatbotservice.infrastructure.SolucaoDeProblemasClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Value(("${facebook.send.api.url}"))
    private String sendApiUrl;
    @Value("${facebook.app.access.token}")
    private String accessToken;

    @Bean
    public ContextManager contextManager() {
        return new MemoryContextManager();
    }

    @Bean
    public UserService userService(SolucaoDeProblemasClient client) {
        return new UserService(client);
    }

    @Bean
    public List<ConjuntoRegra> conjuntoRegras(SolucaoDeProblemasClient client) {
        return Arrays.asList(new ClienteConjuntoRegras(),
                new SolucionadorConjuntoRegras(client),
                new DesconhecidoConjuntoRegras(client)
        );
    }

    @Bean
    public FacebookSendService facebookSendService(RestTemplate restTemplate) {
        return new FacebookSendService(restTemplate, sendApiUrl, accessToken);
    }

    @Bean
    public BotEngine botEngine(List<ConjuntoRegra> conjuntoRegras) {
        return new BotEngine(conjuntoRegras);
    }

    @Bean
    public BotService botService(ContextManager contextManager,
                                 UserService userService, BotEngine botEngine,
                                 FacebookSendService facebookSendService) {
        return new BotService(contextManager, userService, botEngine, facebookSendService);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
