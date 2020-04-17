package com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Builder @ToString
public class AttachmentBotMessage {
    private String type;
    private AttachmentBotMessagePayload payload;
}
