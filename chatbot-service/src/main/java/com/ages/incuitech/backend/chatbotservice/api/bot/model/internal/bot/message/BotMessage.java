package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.Getter;

import java.util.Map;

@Getter
public abstract class BotMessage {
    private Map<String, Object> contexto;
    private BotMessageType type;

    public BotMessage(Map<String, Object> contexto, BotMessageType type) {
        this.contexto = contexto;
        this.type = type;
    }
}