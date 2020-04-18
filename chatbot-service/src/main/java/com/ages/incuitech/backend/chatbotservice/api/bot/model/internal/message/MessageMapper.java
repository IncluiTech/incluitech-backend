package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.User;
import com.ages.incuitech.backend.chatbotservice.api.bot.model.incoming.UserMessage;

import java.util.HashMap;

public class MessageMapper {
    
        private MessageMapper(){

        }

        public static InternalMessage mapToModel(UserMessage message, User user){
                return InternalMessage.builder()
                        .user(user)
                        .contexto(new HashMap<String, Object>())
                        .messageType(MessageType.TEXT)
                        .payload(message.getEntry().toString())
                        .build();
        }
    
}
