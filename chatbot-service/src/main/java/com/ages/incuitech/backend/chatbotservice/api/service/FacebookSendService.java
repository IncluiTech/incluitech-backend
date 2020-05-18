package com.ages.incuitech.backend.chatbotservice.api.service;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.FacebookMessage;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class FacebookSendService {
  private final RestTemplate restTemplate;
  private final String sendAPIUrl;
  private final String accessToken;

  public FacebookSendService(RestTemplate restTemplate, String sendAPIUrl, String accessToken) {
    this.restTemplate = restTemplate;
    this.sendAPIUrl = sendAPIUrl;
    this.accessToken = accessToken;
  }

  public void sendMessage(List<FacebookMessage> botMessages) {
    botMessages.forEach(
        botMessage -> {
          try {
            log.info("Sending message to facebook " + botMessage);
            HttpHeaders headers = new HttpHeaders();
            headers.put(
                HttpHeaders.CONTENT_TYPE,
                Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));
            HttpEntity<FacebookMessage> entity = new HttpEntity<>(botMessage, headers);
            restTemplate.postForEntity(getSendAPIUrl(), entity, String.class);
          } catch (HttpStatusCodeException ex) {
            log.error("Error trying to send message to Facebook Send Api", ex);
          }
        });
  }

  private String getSendAPIUrl() {
    return this.sendAPIUrl + "?access_token=" + this.accessToken;
  }
}
