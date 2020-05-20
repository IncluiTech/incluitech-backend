package com.ages.incuitech.backend.chatbotservice.infrastructure;

import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.*;
import com.google.gson.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Slf4j
@Component
@AllArgsConstructor
public class SolucaoDeProblemasClient {

    private RestTemplate restTemplate;
    private Gson gson;
    private SolucaoDeProblemasProperties properties;

    public void saveSolucionador(SolucionadorRequest solucionadorRequest) {
        HttpEntity<SolucionadorRequest> request = new HttpEntity<>(solucionadorRequest);

        log.info(String.format("Iniciando chamada REST para solucao-de-problemas-service para salvar solucionador:  %s",
                solucionadorRequest));

        try {
            restTemplate.postForEntity(properties.getUrl() + properties.getUri(), request, SolucionadorRequest.class);
        } catch (HttpStatusCodeException error) {
            log.error(String.format(
                    "Erro na chamada REST para solucao-de-problemas-service para salvar solucionador: %s. reponse {%s}",
                    gson.toJson(solucionadorRequest), error.getMessage()));
            throw error;
        }
    }

    public void updateSolucionador(SolucionadorRequest solucionadorRequest) {
        HttpEntity<SolucionadorRequest> request = new HttpEntity<>(solucionadorRequest);

        log.info(String.format("Iniciando chamada REST para solucao-de-problemas-service para atualizar solucionador:  %s",
                solucionadorRequest));

        try {
            restTemplate.put(properties.getUrl() + properties.getUri(), request, SolucionadorRequest.class);
        } catch (HttpStatusCodeException error) {
            log.error(String.format(
                    "Erro na chamada REST para solucao-de-problemas-service para atualizar solucionador: %s. reponse: ",
                    gson.toJson(request)), error);
            throw error;
        }
    }

    public SolucionadorRequest getByFacebookId(String facebookId) {
        ResponseEntity<SolucionadorRequest> response = restTemplate
                .getForEntity(properties.getUrl() + properties.getUri() + "/" + facebookId, SolucionadorRequest.class);
        return response.getBody();
    }

}
