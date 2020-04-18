package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ButtonComponentBotMessage extends ComponentBotMessage {
    private String texto;
    private List<Button> botoes;
    public ButtonComponentBotMessage(String texto, List<Button> botoes) {
        super(ComponentBotMessageType.BOTAO);
        this.texto = texto;
        this.botoes = botoes;
    }
}
