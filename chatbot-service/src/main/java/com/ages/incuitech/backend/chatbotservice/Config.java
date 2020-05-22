package com.ages.incuitech.backend.chatbotservice;

import com.ages.incuitech.backend.chatbotservice.api.service.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.cliente.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.desconhecido.*;
import com.ages.incuitech.backend.chatbotservice.business.conjunto.solucionador.*;
import com.ages.incuitech.backend.chatbotservice.business.service.*;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.*;
import com.ages.incuitech.backend.chatbotservice.infrastructure.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

import java.util.*;

@Configuration
public class Config {

    @Value(("${facebook.send.api.url}"))
    private String sendApiUrl;

    @Value("${facebook.app.access.token}")
    private String accessToken;

    @Value("${spring.social.facebook.graphUrl}")
    private String graphURL;

    @Bean
    public ContextManager contextManager() {
        return new MemoryContextManager();
    }

    @Bean
    public UserService userService(SolucaoDeProblemasClient client) {
        return new UserService(client);
    }

    @Bean
    public FacebookService facebookService(RestTemplate template) {
        return new FacebookService(template, graphURL, accessToken);
    }

    @Bean
    public List<ConjuntoRegra> conjuntoRegras(SolucaoDeProblemasClient client, FacebookService service) {
        return Arrays.asList(new ClienteConjuntoRegras(),
                new SolucionadorConjuntoRegras(client),
                new DesconhecidoConjuntoRegras(client, service)
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
