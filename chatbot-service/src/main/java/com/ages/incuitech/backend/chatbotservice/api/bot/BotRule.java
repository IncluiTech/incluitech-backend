package com.ages.incuitech.backend.chatbotservice.api.bot;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.BotMessage;

import java.util.Map;

public interface BotRule {
    boolean verify(Object object, Map<String, Object> contexto);

    BotMessage process(Object object, Map<String, Object> contexto);
}
