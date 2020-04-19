package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.Card;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Classe que representa um carrousel, contendo em si, uma lista dos Cards do carrousel.
 * @see Card
 */
@Getter @ToString
public class CarrouselComponentBotMessage extends ComponentBotMessage {
    private List<Card> elementos;
    public CarrouselComponentBotMessage(List<Card> elementos) {
        super(ComponentBotMessageType.CARROSSEL);
        this.elementos = elementos;
    }
}
