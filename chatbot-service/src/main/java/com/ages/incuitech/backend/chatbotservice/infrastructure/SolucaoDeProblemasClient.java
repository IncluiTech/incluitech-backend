package com.ages.incuitech.backend.chatbotservice.infrastructure;

import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.*;
import com.ages.incuitech.backend.chatbotservice.infrastructure.cliente.*;
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

    public void saveCliente(ClienteRequest clienteRequest) {
        HttpEntity<ClienteRequest> request = new HttpEntity<>(clienteRequest);

        log.info(String.format("Iniciando chamada REST para solucao-de-problemas-service para salvar cliente:  %s",
                clienteRequest));

        try {
            restTemplate.postForEntity(properties.getUrl() + properties.getUriCliente(), request, ClienteRequest.class);
        } catch (HttpStatusCodeException error) {
            log.error(String.format(
                    "Erro na chamada REST para solucao-de-problemas-service para salvar cliente: %s. reponse {%s}",
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
                    "Erro na chamada REST para solucao-de-problemas-service para atualizar solucionador: %s. reponse: ",
                    gson.toJson(request)), error);
            throw error;
        }
    }

    public void updateCliente(ClienteRequest clienteRequest) {
        HttpEntity<ClienteRequest> request = new HttpEntity<>(clienteRequest);

        log.info(String.format("Iniciando chamada REST para solucao-de-problemas-service para atualizar cliente:  %s",
                clienteRequest));

        try {
            restTemplate.put(properties.getUrl() + properties.getUriCliente(), request, ClienteRequest.class);
        } catch (HttpStatusCodeException error) {
            log.error(String.format(
                    "Erro na chamada REST para solucao-de-problemas-service para atualizar cliente: %s. reponse: ",
                    gson.toJson(request)), error);
            throw error;
        }
    }

    public SolucionadorRequest getByFacebookId(String facebookId) {
        ResponseEntity<SolucionadorRequest> response = restTemplate
                .getForEntity(properties.getUrl() + properties.getUri() + "/" + facebookId, SolucionadorRequest.class);
        return response.getBody();
    }

    public ClienteRequest getClienteByFacebookId(String facebookId) {
        ResponseEntity<ClienteRequest> response = restTemplate
                .getForEntity(properties.getUrl() + properties.getUriCliente() + "/" + facebookId, ClienteRequest.class);
        return response.getBody();
    }

}
