package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CarrouselBotMessage extends BotMessage {
    private List<Card> elementos;
    public CarrouselBotMessage(Map<String, Object> contexto, List<Card> elementos) {
        super(contexto, BotMessageType.CARROSSEL);
        this.elementos = elementos;
    }
}
