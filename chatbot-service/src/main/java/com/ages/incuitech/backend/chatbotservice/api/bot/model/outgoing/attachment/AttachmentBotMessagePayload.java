package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
public abstract class AttachmentBotMessagePayload {
  @JsonProperty("template_type")
  private final String templateType;

  public AttachmentBotMessagePayload(String templateType) {
    this.templateType = templateType;
  }
}
