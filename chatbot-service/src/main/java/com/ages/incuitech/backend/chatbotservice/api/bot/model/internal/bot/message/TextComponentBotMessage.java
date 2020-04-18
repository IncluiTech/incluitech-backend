package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.Getter;

import java.util.Map;

@Getter
public class TextComponentBotMessage extends ComponentBotMessage {
    private String text;
    public TextComponentBotMessage(String text) {
        super(ComponentBotMessageType.TEXTO);
        this.text = text;
    }
}
