package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@With
public class BotMessage {
    private final Map<String, Object> contexto;
    private List<ComponentBotMessage> messages = Collections.emptyList();
}
