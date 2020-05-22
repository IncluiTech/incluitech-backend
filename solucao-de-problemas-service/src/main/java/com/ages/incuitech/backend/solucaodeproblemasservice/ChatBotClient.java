package com.ages.incuitech.backend.solucaodeproblemasservice;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.cliente.*;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.*;
import com.google.gson.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import static java.lang.String.*;

@Slf4j
@Component
@AllArgsConstructor
public class ChatBotClient {

    private RestTemplate restTemplate;
    private Gson gson;
    private ChatBotProperties properties;

    public void enviarMensagemCadastroSucesso(String facebookId) {
        String url = properties.getUrl() + properties.getUri() + "/" + facebookId;
        try {
            log.info(format("REST para chatbot-service para notificar sucesso cadastro:  %s", facebookId));
            this.restTemplate.put(url, SolucionadorRequest.class);
        } catch (HttpStatusCodeException error) {
            log.error(format("ERRO ao chamar chatbot-service para notificar sucesso cadastro: %s.", facebookId), error);
            throw error;
        }
    }
}
