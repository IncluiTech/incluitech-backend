package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.QuickReplyButton;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter @ToString
public class QuickReplyBotMessage extends BotMessage {
    private String texto;
    private List<QuickReplyButton> quickReplyButtons;
    public QuickReplyBotMessage(Map<String, Object> contexto, String texto, List<QuickReplyButton> quickReplyButtons) {
        super(contexto, BotMessageType.QUICK_REPLY);
        this.texto = texto;
        this.quickReplyButtons = quickReplyButtons;
    }
}
