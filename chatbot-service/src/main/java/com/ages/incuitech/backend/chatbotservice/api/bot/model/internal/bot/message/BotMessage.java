package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.*;
import lombok.*;

import java.util.*;


/**
 * Classe represtantiva de uma mensagm do bot.
 * Contem o contexto da conversa no momento que a mensagem é gerada
 * e um lista das mensagem que bot ira falar
 *
 * @see ComponentBotMessage
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@With
public class BotMessage {
    private final Context contexto;
    private List<ComponentBotMessage> messages = Collections.emptyList();

    public BotMessage withMessages(ComponentBotMessage... messages) {
        this.messages = new ArrayList<>(Arrays.asList(messages));
        return this;
    }

}
