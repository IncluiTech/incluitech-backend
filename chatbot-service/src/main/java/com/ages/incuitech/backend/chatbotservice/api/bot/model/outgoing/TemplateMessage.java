package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Builder @ToString
public class TemplateMessage {
    private String text;
    @JsonProperty("quick_replies")
    private List<QuickReplyButton> quickRepliesButtons;
    private AttachmentBotMessage attachment;
}
