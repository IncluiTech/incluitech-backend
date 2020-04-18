package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FacebookMessage {
    @JsonProperty("messaging_type")
    private String messageType;
    private UserRecipient recipient;
    private TemplateMessage message;
}
