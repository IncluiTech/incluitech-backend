package com.ages.incuitech.backend.chatbotservice.infrastructure;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message.UsuarioDaMensagem;
import com.ages.incuitech.backend.chatbotservice.business.domain.ProblemaDoCliente;
import com.ages.incuitech.backend.chatbotservice.infrastructure.User.UserRequest;
import com.ages.incuitech.backend.chatbotservice.infrastructure.solucionador.*;
import com.ages.incuitech.backend.chatbotservice.infrastructure.cliente.*;
import com.google.gson.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import java.util.*;

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
            restTemplate.postForEntity(properties.getUrl() + properties.getUriSolucionador(), request, SolucionadorRequest.class);
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

    public List<ProblemaDoCliente> getProblemasOfClient(String clientFacebookId) {
        ClienteRequest clienteRequest = getClienteByFacebookId(clientFacebookId).orElseThrow(RuntimeException::new);
        String url = properties.getUrl() + properties.getUriProblemas() + "/" + clienteRequest.getId();
        return List.of(Objects.requireNonNull(restTemplate.getForEntity(url, ProblemaDoCliente[].class).getBody()));
    }

    public void salvaProblemaCliente(ProblemaDoCliente problemaDoCliente, UsuarioDaMensagem usuario) {
        ClienteRequest clienteRequest = getClienteByFacebookId(usuario.getId()).orElseThrow(RuntimeException::new);
        Map<String, Object> problemaBody = Map.of(
                "titulo", problemaDoCliente.getTitulo(),
                "descricao", problemaDoCliente.getDescricao(),
                "idCliente", clienteRequest.getId()
        );
        String url = properties.getUrl() + properties.getUriProblemas();
        restTemplate.postForEntity(url, new HttpEntity<>(problemaBody), String.class);
    }

    public void updateSolucionador(SolucionadorRequest solucionadorRequest) {
        HttpEntity<SolucionadorRequest> request = new HttpEntity<>(solucionadorRequest);

        log.info(String.format("Iniciando chamada REST para solucao-de-problemas-service para atualizar solucionador:  %s",
                solucionadorRequest));

        try {
            restTemplate.put(properties.getUrl() + properties.getUriSolucionador(), request, SolucionadorRequest.class);
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

    public Optional<? extends UserRequest> getByFacebookId(String facebookId) {
        Optional<SolucionadorRequest> userRequest = getSolucionadorByFacebookId(facebookId);
        if (userRequest.isEmpty()) return getClienteByFacebookId(facebookId);
        return userRequest;
    }

    public Optional<SolucionadorRequest> getSolucionadorByFacebookId(String facebookId) {
        try {
            ResponseEntity<SolucionadorRequest> response = restTemplate
                    .getForEntity(properties.getUrl() + properties.getUriSolucionador() + "/" + facebookId, SolucionadorRequest.class);

            return Optional.ofNullable(response.getBody());
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return Optional.empty();
            else throw e;
        }

    }

    public Optional<ClienteRequest> getClienteByFacebookId(String facebookId) {
        try {
            ResponseEntity<ClienteRequest> response = restTemplate
                    .getForEntity(properties.getUrl() + properties.getUriCliente() + "/" + facebookId, ClienteRequest.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return Optional.empty();
            else throw e;
        }
    }
}
