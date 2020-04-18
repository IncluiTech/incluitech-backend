package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.Getter;

import java.util.Map;

@Getter
public class TextBotMessage extends BotMessage {
    private String text;
    public TextBotMessage(Map<String, Object> contexto, String text) {
        super(contexto, BotMessageType.TEXT);
        this.text = text;
    }
}
