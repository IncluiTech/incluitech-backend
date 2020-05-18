package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.attachment;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TemplateMessage {
  private String text;

  @JsonProperty("quick_replies")
  private List<QuickReplyButton> quickRepliesButtons;

  private AttachmentBotMessage attachment;
}
