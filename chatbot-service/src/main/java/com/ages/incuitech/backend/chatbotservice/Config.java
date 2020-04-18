package com.ages.incuitech.backend.chatbotservice;

import com.ages.incuitech.backend.chatbotservice.api.service.FacebookSendService;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.ConjuntoRegra;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.cliente.ClienteConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.desconhecido.DesconhecidoConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.conjuto.solucionador.SolucionadorConjuntoRegras;
import com.ages.incuitech.backend.chatbotservice.business.service.BotService;
import com.ages.incuitech.backend.chatbotservice.business.service.UserService;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.ContextManager;
import com.ages.incuitech.backend.chatbotservice.business.service.contexto.MemoryContextManager;
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
    @Value("${facebook.app.acess.token}")
    private String accessToken;

    @Bean
    public ContextManager contextManager() {
        return new MemoryContextManager();
    }

    @Bean
    public UserService userService(RestTemplate restTemplate) {
        return new UserService(restTemplate);
    }

    @Bean
    public List<ConjuntoRegra> conjuntoRegras() {
        return Arrays.asList(new ClienteConjuntoRegras(),
                new SolucionadorConjuntoRegras(),
                new DesconhecidoConjuntoRegras()
        );
    }

    @Bean
    public FacebookSendService facebookSendService(RestTemplate restTemplate) {
        return new FacebookSendService(restTemplate, sendApiUrl, accessToken);
    }

    @Bean
    public BotService botService(ContextManager contextManager,
                                 UserService userService, List<ConjuntoRegra> conjuntoRegras,
                                 FacebookSendService facebookSendService) {
        return new BotService(contextManager, userService, conjuntoRegras, facebookSendService);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
