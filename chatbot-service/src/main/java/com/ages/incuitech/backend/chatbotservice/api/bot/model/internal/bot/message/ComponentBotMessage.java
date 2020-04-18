package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public abstract class ComponentBotMessage {
    private ComponentBotMessageType type;

    public ComponentBotMessage(ComponentBotMessageType type) {
        this.type = type;
    }
}