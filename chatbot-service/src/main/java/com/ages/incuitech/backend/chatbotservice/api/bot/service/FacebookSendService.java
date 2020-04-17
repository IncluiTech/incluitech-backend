package com.ages.incuitech.backend.chatbotservice.api.bot.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.Event;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookSendService {
    private final RestTemplate restTemplate;
    private final String sendAPIUrl;
    private final String accessToken;

    public FacebookSendService(RestTemplate restTemplate, String sendAPIUrl, String accessToken) {
        this.restTemplate = restTemplate;
        this.sendAPIUrl = sendAPIUrl;
        this.accessToken = accessToken;
    }

    public ResponseEntity<Response> sendMessage(Event event) {
        return this.restTemplate.postForEntity(this.getSendAPIUrl(), event, Response.class);
    }

    private String getSendAPIUrl() {
        return this.sendAPIUrl + "?access_token=" + this.accessToken;
    }
}
