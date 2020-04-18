package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.User;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message.BotMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

public class MessageMapper {
    
        private MessageMapper(){

        }

        public static InternalMessage mapToModel(BotMessage message, User user){
                return InternalMessage.builder()
                        .user(user)
                        .map(message.getContexto())
                        .messageType(message.getType())
                        .payload()
                        .build();
        }
    
}
