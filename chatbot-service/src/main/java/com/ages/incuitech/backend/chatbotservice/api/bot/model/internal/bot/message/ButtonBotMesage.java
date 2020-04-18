package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ButtonBotMesage extends BotMessage{
    private String text;
    private List<Button> buttons;
    public ButtonBotMesage(Map<String, Object> contexto, String text, List<Button> buttons) {
        super(contexto, BotMessageType.BUTTON);
        this.text = text;
        this.buttons = buttons;
    }
}
