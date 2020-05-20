package com.ages.incuitech.backend.solucaodeproblemasservice;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

@Getter
@Component
public class ChatBotProperties {
    @Value("${url.chatbot-service}")
    private String url;

    @Value("${uri.chatbot-event}")
    private String uri;
}
