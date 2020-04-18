package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import java.util.Map;

public class TextBotMessage extends BotMessage {
    public TextBotMessage(Map<String, Object> contexto) {
        super(contexto, BotMessageType.TEXT);
    }
}
