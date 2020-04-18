package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import lombok.*;

import java.util.List;

@Getter
@ToString
public class CarouselAttachmentPayload extends AttachmentBotMessagePayload {
    private List<Card> elements;

    public CarouselAttachmentPayload(List<Card> elementos) {
        super(AttachmentTypes.GENERIC.getValor());
        this.elements = elementos;
    }
}
