package com.ages.incuitech.backend.solucaodeproblemasservice;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.*;
import com.google.gson.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

@Slf4j
@Component
@AllArgsConstructor
public class ChatBotClient {

    private RestTemplate restTemplate;
    private Gson gson;
    private ChatBotProperties properties;

    public void enviarMensagemCadastroSucesso(ClienteRequest clienteRequest) {
        HttpEntity<ClienteRequest> request = new HttpEntity<ClienteRequest>(clienteRequest);

        this.restTemplate.getForEntity(properties.getUrl() + properties.getUri(), request)

//        log.info(String.format("Iniciando chamada REST para chatbot-service para salvar :  %s",
//                clienteRequest));

//        try {
//            restTemplate.postForEntity(properties.getUrl() + properties.getUri(), request, SolucionadorRequest.class);
//        } catch (HttpStatusCodeException error) {
//            log.error(String.format(
//                    "Erro na chamada REST para solucao-de-problemas-service para salvar solucionador: %s. reponse {%s}",
//                    gson.toJson(clienteRequest), error.getMessage()));
//            throw error;
//        }
    }
}
