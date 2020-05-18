package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AttachmentBotMessage {
  private String type;
  private AttachmentBotMessagePayload payload;

  public AttachmentBotMessage(AttachmentBotMessagePayload payload) {
    this.payload = payload;
    this.type = "template";
  }
}
