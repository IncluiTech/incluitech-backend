package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.User;
import lombok.*;

import java.util.Map;

@AllArgsConstructor
@ToString @Getter @Setter
@Builder
public class InternalMessage {
    private User user;
    private Map<String, Object> contexto;
    private MessageType messageType;
    private String payload;
}
