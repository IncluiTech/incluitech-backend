package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import java.util.List;
import lombok.*;

@Getter
@ToString
public class CarouselAttachmentPayload extends AttachmentBotMessagePayload {
  private List<Card> elements;

  public CarouselAttachmentPayload(List<Card> elementos) {
    super(AttachmentTypes.GENERIC.getValor());
    this.elements = elementos;
  }
}
