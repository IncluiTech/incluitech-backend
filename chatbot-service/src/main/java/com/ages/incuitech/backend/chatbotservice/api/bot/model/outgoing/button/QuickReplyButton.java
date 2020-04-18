package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class QuickReplyButton {
    @JsonProperty("content_type")
    private String contentType;
    private String title;
    private String payload;
}
