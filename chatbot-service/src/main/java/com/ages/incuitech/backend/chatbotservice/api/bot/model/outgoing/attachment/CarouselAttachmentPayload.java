package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import lombok.*;

import java.util.List;

@Getter
@ToString
public class CarouselAttachmentPayload extends AttachmentBotMessagePayload {
    public CarouselAttachmentPayload() {
        super(AttachmentTypes.GENERIC.getValor());
    }

    private List<Card> elements;
}
