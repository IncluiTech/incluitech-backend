package com.ages.incuitech.backend.chatbotservice.api.bot.model.internal.bot.message;

import com.ages.incuitech.backend.chatbotservice.api.bot.model.outgoing.button.Button;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class ButtonBotMesage extends BotMessage{
    private String texto;
    private List<Button> botoes;
    public ButtonBotMesage(Map<String, Object> contexto, String texto, List<Button> botoes) {
        super(contexto, BotMessageType.BOTAO);
        this.texto = texto;
        this.botoes = botoes;
    }
}
