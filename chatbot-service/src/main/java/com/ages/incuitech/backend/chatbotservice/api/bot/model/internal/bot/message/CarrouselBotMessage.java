package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CarrouselBotMessage extends BotMessage {
    private List<Card> elements;
    public CarrouselBotMessage(Map<String, Object> contexto, List<Card> elements) {
        super(contexto, BotMessageType.CARROUSEL);
        this.elements = elements;
    }
}
