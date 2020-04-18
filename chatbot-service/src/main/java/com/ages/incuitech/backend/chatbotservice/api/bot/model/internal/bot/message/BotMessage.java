package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.QuickReplyButton;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import lombok.Getter;

import java.util.List;
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