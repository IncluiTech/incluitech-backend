package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment.TemplateMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@ToString
public class FacebookMessage {
  @JsonProperty("messaging_type")
  private String messageType = MessageType.RESPONSE.name();

  private String tag;
  private UserRecipient recipient;
  private TemplateMessage message;
}
