package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.QuickReplyButton;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class QuickReplyBotMessage extends BotMessage {
    private String text;
    private List<QuickReplyButton> quickReplyButtons;
    public QuickReplyBotMessage(Map<String, Object> contexto, String text, List<QuickReplyButton> quickReplyButtons) {
        super(contexto, BotMessageType.QUICK_REPLY);
        this.text = text;
        this.quickReplyButtons = quickReplyButtons;
    }
}
