package com.ages.incuitech.backend.chatbotservice.infrastructure;

import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.SolucionadorRequest;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

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
                    gson.toJson(request), error.getMessage()));
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
                    "Erro na chamada REST para solucao-de-problemas-service para atualizar solucionador: %s. reponse {%s}",
                    gson.toJson(request), error.getMessage()));
            throw error;
        }
    }

    public SolucionadorRequest getByFacebookId(String facebookId) {
        ResponseEntity<SolucionadorRequest> response = restTemplate
                .getForEntity(properties.getUrl() + properties.getUri() + "/" + facebookId, SolucionadorRequest.class);
        return response.getBody();
    }

}
