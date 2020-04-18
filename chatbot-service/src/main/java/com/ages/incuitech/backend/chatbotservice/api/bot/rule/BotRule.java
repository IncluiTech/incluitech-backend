package com.ages.incuitech.backend.chatbotservice.api.bot.rule;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;

import java.util.Map;

public interface BotRule {
    boolean verify(InternalMessage message, Map<String, Object> contexto);

    BotMessage process(InternalMessage message, Map<String, Object> contexto);
}
